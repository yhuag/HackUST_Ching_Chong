package whatever.whatever;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

/**
 * Created by msi on 2016/4/16.
 */
public class DecoderActivity extends Activity implements QRCodeReaderView.OnQRCodeReadListener {

    private TextView myTextView;
    private QRCodeReaderView mydecoderview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_decoder);

        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);

        myTextView = (TextView) findViewById(R.id.exampleTextView);
    }


    // Called when a QR is decoded
    // "text" : the text encoded in QR
    // "points" : points where QR control points are placed
    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        myTextView.setText(text);
        Intent intent = new Intent(this,CardViewActivity1.class);
        //**************************GET FROM DATABASE!
        String restaurant_name = text;
        intent.putExtra("RESTAURANT_NAME", restaurant_name);
        startActivity(intent);
    }


    // Called when your device have no camera
    @Override
    public void cameraNotFound() {

    }

    // Called when there's no QR codes in the camera preview image
    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mydecoderview.getCameraManager().stopPreview();
    }
}
