package com.sourceedge.bhagyalakshmi.orders.support;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;
import com.sourceedge.bhagyalakshmi.orders.R;
import com.sourceedge.bhagyalakshmi.orders.changepassword.ChangePassword;
import com.sourceedge.bhagyalakshmi.orders.distributorsales.controller.RetailerLookUp;
import com.sourceedge.bhagyalakshmi.orders.location.controller.Location;
import com.sourceedge.bhagyalakshmi.orders.login.Login;
import com.sourceedge.bhagyalakshmi.orders.orders.controller.AdminOrders;
import com.sourceedge.bhagyalakshmi.orders.orders.controller.DistributorOrders;
import com.sourceedge.bhagyalakshmi.orders.orders.controller.DistributorSalesOrders;
import com.sourceedge.bhagyalakshmi.orders.orders.controller.SalesPersonOrders;

import org.json.JSONObject;


/**
 * Created by Centura on 01-12-2016.
 */

public class Class_Genric {

    public static final String MyPref = "MyPref";
    public static final String Sp_LoginType = "LoginType";
    public static final int ADMIN=1;
    public static final int DISTRIBUTORSALES=2;
    public static final int DISTRIBUTOR=3;
    public static final int SALESPERSON=4;
    public static final String Sp_Username = "Username";
    public static final String Sp_Password = "Password";
    public static boolean progressAlive = false;
    static ProgressDialog pDialog;

    static Button button;
    static Button button1;

    public static LinearLayout myProfile, changePassword, location, distributorSalesMyOrders, activeOrders, distributorMyOrders,salesmanMyOrders,salesDistributorRetailers,retailers, distributorSalesPayments,distributorPayments,salesmanPayments, messages, logout;
    public static Activity a;
    static SharedPreferences sharedPreferences;

    public static void ShowDialog(Context context, String message, Boolean flag) {
        if (flag) {
            if (progressAlive) {
                pDialog.cancel();
                progressAlive = false;
            }
            {
               /* pDialog=new Dialog(context);
                pDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                pDialog.setContentView(R.layout.customload);*/

                pDialog = new ProgressDialog(context);
                pDialog.setMessage(message);
                if (message.contains("Loading"))
                    pDialog.setCanceledOnTouchOutside(false);
                progressAlive = true;
                pDialog.show();
            }
        } else {
            if (progressAlive) {
                pDialog.dismiss();
                pDialog.cancel();
                progressAlive = false;
            }
        }
    }

    public static void setOrientation(Context mContext) {
        if (mContext.getResources().getBoolean(R.bool.isPhone))
            ((Activity) mContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            ((Activity) mContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public static void applyFontForToolbarTitle(Toolbar toolbar,Activity context){
        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);
            if(view instanceof TextView){
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.
                        createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Regular.ttf");
                tv.setTypeface(Typeface.SANS_SERIF,Typeface.NORMAL);
            }
        }
    }


    public static void setupDrawer(Toolbar toolbar, DrawerLayout drawer, ActionBarDrawerToggle mDrawerToggle, Context mContext) {
        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle((Activity) mContext, drawer, toolbar, R.string.opendrawer, R.string.closedrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State
    }

    public static boolean NetCheck(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected == false) {
            /*Toast.makeText(context, "Please Check Internet Connectivity", Toast.LENGTH_LONG).show();
            return false;*/
            try {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.checkinternet);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                Button btn = (Button) dialog.findViewById(R.id.btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        NetCheck(context);
                    }
                });
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return true;
    }

    public static boolean success(JSONObject result) {
        if (result.optString("IsSuccess").matches("true"))
            return true;
        else return false;
    }

    public static String getError(JSONObject result) {
        String Error = result.optString("Errors");
        if (Error != null)
            if (Error.length() > 2)
                Error = Error.substring(1, Error.length() - 2);
        return Error;
    }


    public static void apiError(final Context context, VolleyError volleyError) {
        if (volleyError instanceof NoConnectionError) {
            try {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.checkinternet);
                dialog.show();
                Button btn = (Button) dialog.findViewById(R.id.btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        //NetCheck(context);
                    }
                });
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            Toast.makeText(context, "Server Down! Please get back Later", Toast.LENGTH_SHORT).show();
        }
    }

    public static int getType(String Sp_LoginType) {
        if (Sp_LoginType.matches("Admin"))
            return ADMIN;
        if (Sp_LoginType.matches("Distributor Sales"))
            return DISTRIBUTORSALES;
        if (Sp_LoginType.matches("Distributor"))
            return DISTRIBUTOR;
        else return SALESPERSON;
    }

    public static void drawerOnClicks(final Context context) {
        a = (Activity) context;
        sharedPreferences = a.getSharedPreferences(MyPref, a.MODE_PRIVATE);
        myProfile = (LinearLayout) a.findViewById(R.id.my_profile);
        changePassword = (LinearLayout) a.findViewById(R.id.change_password);
        location = (LinearLayout) a.findViewById(R.id.location);
        distributorSalesMyOrders = (LinearLayout) a.findViewById(R.id.distributor_sales_my_orders);
        distributorMyOrders=(LinearLayout)a.findViewById(R.id.distributor_my_orders);
        salesmanMyOrders=(LinearLayout)a.findViewById(R.id.salesman_my_orders);
        activeOrders = (LinearLayout) a.findViewById(R.id.active_orders);
        salesDistributorRetailers = (LinearLayout) a.findViewById(R.id.sales_distributors_retailers);
        retailers = (LinearLayout) a.findViewById(R.id.retailers);
        distributorSalesPayments = (LinearLayout) a.findViewById(R.id.distributor_sales_payments);
        distributorPayments = (LinearLayout) a.findViewById(R.id.distributor_payments);
        salesmanPayments = (LinearLayout) a.findViewById(R.id.salesman_payments);
        messages = (LinearLayout) a.findViewById(R.id.messages);
        logout = (LinearLayout) a.findViewById(R.id.logout);

        switch (getType(sharedPreferences.getString(Sp_LoginType, ""))) {
            case ADMIN:
                distributorSalesMyOrders.setVisibility(View.GONE);
                distributorMyOrders.setVisibility(View.GONE);
                salesmanMyOrders.setVisibility(View.GONE);
                distributorSalesPayments.setVisibility(View.GONE);
                distributorPayments.setVisibility(View.GONE);
                salesmanPayments.setVisibility(View.GONE);
                retailers.setVisibility(View.GONE);
                location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a.startActivity(new Intent(a, Location.class));
                        a.finish();
                    }
                });

                activeOrders.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a.startActivity(new Intent(a, AdminOrders.class));
                        a.finish();
                    }
                });
                salesDistributorRetailers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;


            case DISTRIBUTORSALES:
                location.setVisibility(View.GONE);
                activeOrders.setVisibility(View.GONE);
                distributorMyOrders.setVisibility(View.GONE);
                salesmanMyOrders.setVisibility(View.GONE);
                distributorPayments.setVisibility(View.GONE);
                salesmanPayments.setVisibility(View.GONE);
                salesDistributorRetailers.setVisibility(View.GONE);
                distributorSalesMyOrders.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a.startActivity(new Intent(a, DistributorSalesOrders.class));
                        a.finish();
                    }
                });

                retailers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a.startActivity(new Intent(a, RetailerLookUp.class));
                        a.finish();
                    }
                });

                distributorSalesPayments.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;


            case DISTRIBUTOR:
                location.setVisibility(View.GONE);
                activeOrders.setVisibility(View.GONE);
                distributorSalesMyOrders.setVisibility(View.GONE);
                salesmanMyOrders.setVisibility(View.GONE);
                retailers.setVisibility(View.GONE);
                distributorSalesPayments.setVisibility(View.GONE);
                salesmanPayments.setVisibility(View.GONE);
                salesDistributorRetailers.setVisibility(View.GONE);
                distributorMyOrders.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a.startActivity(new Intent(a, DistributorOrders.class));
                        a.finish();
                    }
                });

                distributorPayments.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;


            case SALESPERSON:
                location.setVisibility(View.GONE);
                activeOrders.setVisibility(View.GONE);
                distributorSalesMyOrders.setVisibility(View.GONE);
                distributorMyOrders.setVisibility(View.GONE);
                distributorSalesPayments.setVisibility(View.GONE);
                distributorPayments.setVisibility(View.GONE);
                salesDistributorRetailers.setVisibility(View.GONE);
                retailers.setVisibility(View.GONE);
                salesmanMyOrders.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        a.startActivity(new Intent(a, SalesPersonOrders.class));
                        a.finish();
                    }
                });

                salesmanPayments.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
        }
        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.startActivity(new Intent(a, ChangePassword.class));
                a.finish();
            }
        });

        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(a);
            }
        });
    }

    public static void logout(Context context){
        a = ((Activity) context);
        //db = new DbHelper(context);
        sharedPreferences = a.getSharedPreferences(Class_Genric.MyPref, a.MODE_PRIVATE);
        final Dialog dialog = new Dialog(a);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.logout_alert);
        button = (Button) dialog.findViewById(R.id.btn);
        button1 = (Button) dialog.findViewById(R.id.btn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().commit();
                ModelDB.ClearDB();
                a.startActivity(new Intent(a, Login.class));
                a.finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
