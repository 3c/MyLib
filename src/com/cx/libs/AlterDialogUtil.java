package com.cx.libs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

/**
 * alert dialog tools
 * 
 * @author CX
 * 
 */
public class AlterDialogUtil {

	public interface EditDialogListener {
		public void onEditDown(String content);

	}

	public static void showEditDialog(Context con, String title, String message, final EditDialogListener m) {
		final EditText edit = new EditText(con);
		edit.setText(message);
		AlertDialog.Builder dialog = new AlertDialog.Builder(con);
		dialog.setTitle(title);
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setView(edit);
		dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				m.onEditDown(edit.getText().toString());
			}
		});
		dialog.show();
	}

	public static void openDialog(Context con, String message) {
		AlertDialog.Builder ad = new AlertDialog.Builder(con);
		ad.setTitle(android.R.string.dialog_alert_title);
		ad.setMessage(message);
		ad.setPositiveButton(android.R.string.ok, null);
		ad.show();
	}

	public static void openDialog(Context con, String dialogTitle, String dialogMessage) {
		AlertDialog.Builder ad = new AlertDialog.Builder(con);
		ad.setTitle(dialogTitle);
		ad.setMessage(dialogMessage);
		ad.setPositiveButton(android.R.string.ok, null);
		ad.show();
	}
}
