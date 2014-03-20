package com.mobitide.common.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;

import com.mobitide.lib.R;

/**
 * alert dialog tools
 * 
 * @author CX
 * 
 */

public class MAlertDialogUtil {
    static String content;

    public interface IEditDialogListener {
        public void onEditDown(String content);
    }

    public interface listRadioDialogListener {
        public void onListRadioPressed(String content);
    }

    public static void openDialog(Context con, String dialogTitle, String dialogMessage) {
        AlertDialog.Builder ad = new AlertDialog.Builder(con);
        ad.setTitle(dialogTitle);
        ad.setMessage(dialogMessage);
        ad.setPositiveButton(android.R.string.ok, null);
        ad.show();
    }

    public static void openDialog(Context con, String message) {
        openDialog(con, MResUtil.getString(R.string.dialog_title), message);
    }

    /**
     * 显示一个带有editText 的对话框
     * 
     * @param con
     * @param title
     * @param message
     * @param editDialogListener
     */
    public static void showEditDialog(Context con, String title, String message,
            final IEditDialogListener editDialogListener) {
        final EditText edit = new EditText(con);
        edit.setText(message);
        AlertDialog.Builder dialog = new AlertDialog.Builder(con);
        dialog.setTitle(title);
        dialog.setIcon(android.R.drawable.ic_dialog_info);
        dialog.setView(edit);
        dialog.setPositiveButton(MResUtil.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                editDialogListener.onEditDown(edit.getText().toString());
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }



    public static void openDialog(Context con, String message, final IOnAlertButtonClickedListener listener) {

        openDialog(con, MResUtil.getString(R.string.dialog_title), message, listener);

    }



    public static void openDialog(Context con, String title, String message,
            final IOnAlertButtonClickedListener listener) {

        AlertDialog.Builder ad = new AlertDialog.Builder(con);
        AlertDialog alertDialog = null;
        ad.setTitle(title);
        ad.setMessage(message);
        ad.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                listener.onSureClick();
            }
        });
        ad.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                listener.onCancelClick();
            }
        });
        alertDialog = ad.create();
        alertDialog.show();

    }



    /**
     * 单选按钮对话框
     * 
     * @param con
     * @param message
     */
    public static void showListRadioDialog(Context con, final CharSequence[] items, String title,
            final listRadioDialogListener radioDialogListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        AlertDialog dialog;
        MLogUtil.d("items 0  - " + items[0]);
        content = items[0].toString();
        builder.setSingleChoiceItems(items, 0, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                content = items[which].toString();
            }
        });
        builder.setTitle(title);
        builder.setPositiveButton(R.string.ok, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                radioDialogListener.onListRadioPressed(content);
                dialog.cancel();
            }
        });
        builder.setNegativeButton(R.string.cancel, new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    /**
     * 多选按钮对话框
     * 
     * @param con
     * @param items
     * @param selected
     * @param radioDialogListener
     */
    public static void showMultiChoiceDialog(Context con, String title, final CharSequence[] items,
            final boolean[] selected, final listRadioDialogListener radioDialogListener) {
        Builder builder = new AlertDialog.Builder(con);
        AlertDialog dialog;
        builder.setTitle(title);
        // builder.setIcon(R.drawable.basketball);
        DialogInterface.OnMultiChoiceClickListener mutiListener = new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                selected[which] = isChecked;
            }
        };
        builder.setMultiChoiceItems(items, selected, mutiListener);
        DialogInterface.OnClickListener btnListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String selectedStr = "";
                for (int i = 0; i < selected.length; i++) {
                    if (selected[i] == true) {
                        selectedStr = selectedStr + " " + items[i];
                    }
                }
                radioDialogListener.onListRadioPressed(selectedStr);
            }
        };
        builder.setPositiveButton(MResUtil.getString(R.string.ok), btnListener);
        dialog = builder.create();
        dialog.show();

    }



}
