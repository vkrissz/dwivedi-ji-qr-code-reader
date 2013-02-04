package com.dwivedi.qr_codereader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView tvResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpView();
	}

	private void setUpView() {
		// TODO Auto-generated method stub
		tvResult = (TextView) this.findViewById(R.id.textViewResult);
		Button btnScanQRCode = (Button) this
				.findViewById(R.id.buttonScanQrCode);
		btnScanQRCode.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				IntentIntegrator integrator = new IntentIntegrator(
						MainActivity.this);
				integrator.initiateScan();

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		IntentResult scanResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, data);
		if (scanResult != null) {

			// handle scan result
			String contantsString = scanResult.getContents() == null ? "0"
					: scanResult.getContents();
			tvResult.setText(contantsString);
			if (contantsString.equalsIgnoreCase("0")) {
				Toast.makeText(this, "Problem to get the  contant Number",
						Toast.LENGTH_LONG).show();

			} else {
				Toast.makeText(this, contantsString, Toast.LENGTH_LONG).show();

			}

		} else {
			Toast.makeText(this, "Problem to secan the barcode.",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
