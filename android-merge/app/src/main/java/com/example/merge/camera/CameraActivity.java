package com.example.merge.camera;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.merge.Config;
import com.example.merge.R;
import com.example.merge.ThisApplication;
import com.example.merge.databinding.ActivityCameraBinding;
import com.example.merge.product.ProductActivity;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CameraActivity extends LocalizationActivity {
    CameraViewModel viewModel;

    private static final int Image_Capture_Code = 1;
    private String selectedModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLanguage(Config.selectedLanguage);
        setView();

        Button category1 = findViewById(R.id.category1);
        Button category2 = findViewById(R.id.category2);
        Button category3 = findViewById(R.id.category3);
        Button category4 = findViewById(R.id.category4);
        Button category5 = findViewById(R.id.category5);

        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedModel = "ramen";
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });

        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedModel = "snack";
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });

        category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedModel = "drink";
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });

        category4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedModel = "dairy";
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });

        category5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedModel = "icecream";
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Resources resources = ThisApplication.getContext().getResources();
                Toast.makeText(this, resources.getString(R.string.text_loading), Toast.LENGTH_LONG).show();
                final int IMAGE_SIZE = 640;
                int[] pixels = new int[IMAGE_SIZE * IMAGE_SIZE];
                final int NUM_DETECTIONS = 10;

                float[][][] outputLocations;
                float[][] outputClasses;
                float[][] outputScores;
                float[] numDetections;
                Map<Integer, Object> outputMap = new HashMap<>();

                String modelName = selectedModel + ".tflite";
                List<Long> labels = getLabels();

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                bitmap = Bitmap.createScaledBitmap(bitmap, IMAGE_SIZE, IMAGE_SIZE, false);
                bitmap.getPixels(pixels, 0, IMAGE_SIZE, 0, 0, IMAGE_SIZE, IMAGE_SIZE);

                ByteBuffer inputImage = getInputImage(pixels, IMAGE_SIZE);
                Object[] inputArray = {inputImage};
                Interpreter tfliteInterpreter = getTfliteInterpreter(modelName);


                outputLocations = new float[1][NUM_DETECTIONS][4];
                outputClasses = new float[1][NUM_DETECTIONS];
                outputScores = new float[1][NUM_DETECTIONS];
                numDetections = new float[1];

                outputMap.put(0, outputLocations);
                outputMap.put(1, outputClasses);
                outputMap.put(2, outputScores);
                outputMap.put(3, numDetections);

                tfliteInterpreter.runForMultipleInputsOutputs(inputArray, outputMap);
                Long detectedProductID = labels.get((int)outputClasses[0][0]);
                goToProductActivity(detectedProductID);
                // Toast.makeText(this, detectedProductID.toString(), Toast.LENGTH_LONG).show();


            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Aborted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void goToProductActivity(long productId) {
        startActivity(ProductActivity.callingIntentWithProductId(this, productId));
    }

//    ### For Debugging
//    private void printResults(List<String> labels, float[][] outputClasses, float[][] outputScores) {
//
//        for(int i = 0; i < 10; i++) {
//            TextView resultText = findViewById(resultTexts[i]);
//            resultText.setText(labels.get((int) outputClasses[0][i]) + " --- " + String.format("%.5f", outputScores[0][i]));
//        }
//    }

    // TODO : read labels from labelmap.txt
    private List<Long> getLabels() {
        switch (selectedModel){
            case "ramen":
                return Arrays.asList(519L, 530L, 526L, 438L, 538L, 531L, 432L, 520L, 534L, 525L, 532L, 549L, 571L, 426L, 595L, 669L, 592L);
            case "drink":
                return Arrays.asList(3371L, 3399L, 3516L, 3491L, 3518L, 723L, 3285L, 3354L, 3402L, 3343L, 3538L, 3528L, 3486L, 3485L, 3653L, 3648L, 3650L, 3616L, 3645L);
            case "snack":
                return Arrays.asList(6015L, 7736L, 5596L, 5659L, 9182L, 5880L, 5919L, 9192L, 5761L, 5681L, 5340L, 5586L, 6540L, 6216L, 5922L, 6499L, 5349L, 9106L, 6191L);
            case "dairy":
                return Arrays.asList(5189L, 5152L, 4150L, 9186L, 5183L, 4155L, 0L, 5173L, 9184L, 0L, 4417L, 0L, 4382L, 4171L, 9185L, 0L);
            case "icecream":
                return Arrays.asList(1474L, 695L, 1616L, 0L, 1141L, 1507L, 1184L, 1158L, 893L, 1205L, 1621L, 963L, 884L);
            default:
                return Arrays.asList();
        }
    }

    // Load model file into MappedByteBuffer
    // Pass MappedByteBuffer -> Interpreter to perform Object Detection
    private MappedByteBuffer loadModelFile(Activity activity, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    // Create Model Interpreter
    // Must use try-catch block due to exception in loadModelFile()
    private Interpreter getTfliteInterpreter(String modelPath) {
        try {
            return new Interpreter(loadModelFile(CameraActivity.this, modelPath));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ByteBuffer getInputImage(int[] pixels, int imageSize) {
        float IMAGE_MEAN = 127.5f;
        float IMAGE_STD = 127.5f;
        int numBytesPerChannel = 4; // Floating point

        ByteBuffer inputImage = ByteBuffer.allocateDirect(1 * imageSize * imageSize * 3 * numBytesPerChannel);
        inputImage.order(ByteOrder.nativeOrder());

        inputImage.rewind();
        for (int i = 0; i < imageSize; ++i) {
            for (int j = 0; j < imageSize; ++j) {
                int pixelValue = pixels[i * imageSize + j];
                // Float model
                inputImage.putFloat((((pixelValue >> 16) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
                inputImage.putFloat((((pixelValue >> 8) & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
                inputImage.putFloat(((pixelValue & 0xFF) - IMAGE_MEAN) / IMAGE_STD);
            }
        }
        return inputImage;
    }

    private void setView() {
        ActivityCameraBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_camera);
        viewModel = new ViewModelProvider(this).get(CameraViewModel.class);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
    }
}
