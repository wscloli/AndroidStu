package com.example.administrator.text;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    Button start;
    Button stop;
    Button bind;
    Button unBind;
    private MyService.DownloadBinder downloadBinder;
    //成功绑定或断开时调用
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
        downloadBinder=(MyService.DownloadBinder)service;
        downloadBinder.startDownload();
        downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("Disconnected", "onServiceDisconnected: ");
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = (TextView) findViewById(R.id.text);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
         bind= (Button) findViewById(R.id.bind);
        unBind = (Button) findViewById(R.id.unBind);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unBind.setOnClickListener(this);
        //点击启动/关闭服务


        final Button send = (Button) findViewById(R.id.send_request);
            /*send.setOnClickListener(new View.OnClickListener() {
                String httpUrl = "http://192.168.1.112:8080/app/myweb";
                @Override*/
               /* public void onClick(View v) {
                    new AsyncTask<String, Void, String>(){
                        @Override
                        protected String doInBackground(String... params) {
                            return getHttpDoget(params[0]);
                        }
                        protected void onPostExecute(String result) {
                            mShowMsgTxt.setText(result);
                        };
                    }.execute(httpUrl);
                }
            });*/

        //mDopostBtn = (Button) findViewById(R.id.http_dopost_btn);
        send.setOnClickListener(new View.OnClickListener() {
            String http = "http://192.168.0.1:8080/Text/MyServlet";

            @Override
            public void onClick(View v) {
                sendRequest();
            }

            private void sendRequest() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(http);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");  //请求方法
                            conn.setConnectTimeout(15000);  //设置连接超时
                            conn.setReadTimeout(10000);  //设置读取超时
                            conn.connect();  //建立连接

                            /*得到流向服务器传数据*/
                            OutputStream out = conn.getOutputStream();
                            PrintWriter pr = new PrintWriter(out);
                            pr.print("username=小明&password=1234");
                            pr.flush();

                            /*读取服务端数据*/
                            InputStream in = conn.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));  //得到连接服务器的缓冲流
                            String line;
                            StringBuilder sb = new StringBuilder();
                            while ((line = br.readLine()) != null) {   
                                //从服务中读取请求返回的数据
                                Log.i("haha", "run: --------------OK"+conn.getResponseCode());
                                sb.append(line);
                            }
                            showResponse(sb.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                        }
                    }
                }).start();

            }
        });



        /*get请求方式连接服务器   get请求传递参数在url后面加参数*/
      /*  public String getHttpDoget (String httpUrl){
            try {
                // String name = URLEncoder.encode("小红", "utf-8");   //中文输入先指定编码，不然会出现乱码
                //get请求带参数的URL地址   http://192.168.1.112:8080/app/myweb?username=小红&password=abcd
                URL url = new URL(httpUrl + "?username=www" + "&password=abcd");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");    //请求方式
                conn.setConnectTimeout(15000);  //设置连接超时
                conn.setReadTimeout(10000);  //设置读取超时
                conn.connect();  //建立连接

                InputStream in = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));  //得到连接服务器的缓冲流
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {    //从服务中读取请求返回的数据
                    sb.append(line);
                }
                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }*/

        /*Button sendRequest = (Button) findViewById(R.id.send_request);
        text = (TextView) findViewById(R.id.text);
        sendRequest.setOnClickListener(this);
    }

   @Override
   public void onClick(View view) {
        if(view.getId()==R.id.send_request){
            sendRequest();
            AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("提示：");
            dialog.setMessage("请尽快");
            dialog.setCancelable(false);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }

            });
            dialog.show();
        }
    }

    private void sendRequest() {
    new Thread(new Runnable() {
        @Override
        public void run() {


                OkHttpClient client=new OkHttpClient();
                //存放要提交的数据
                RequestBody requestBody=new FormBody.Builder()
                        .add("bitcoin_address=","asdsfdsfsdfdsf")
                        .add("anda_address=","dasfdsafgs")
                        .build();
                 Request request=new Request.Builder()
                        //.url("http://192.168.0.8:8081/chain/transactions/addTxRecord")
                            .url("http://192.168.0.1:8080/Text/MyServlet")
                         .addHeader("Content-Type","application/json;charset=utf-8")
                        .addHeader("Accept","application/json")
                         .post(requestBody)
                        .build();
                //获取服务器返回的数值
            Response response= null;
            try {

                response = client.newCall(request).execute();
            //得到返回数值的内容
            String responseData= null;
                responseData = response.body().string();
            showResponse(responseData);
                if (response.isSuccessful()) {
                    Log.d("haha", "code is "+response);
                } else {
                    Log.d("haha","Unexpected code " + response);
                }
            } catch (Exception e) {
                Log.d("1   IOException", "code is "+response);
                e.printStackTrace();
                }
            }
    }).start();
    }
*/
    /*private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try {
                   // URL url=new URL("http://192.168.0.11:8081/chain/transactions/btcExchangeAnda");
                    URL url=new URL("https://www.bilibili.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    if (connection.getResponseCode() == 200) {
                        Log.d("haha", "code is "+connection.getResponseCode());
                        BufferedInputStream  in = new BufferedInputStream(connection.getInputStream());
                        // InputStream in = connection.getInputStream();
                        reader=new BufferedReader(new InputStreamReader(in));
                        StringBuilder response=new StringBuilder();
                        String line;
                       // Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_LONG).show();
                        while((line=reader.readLine())!=null){
                            response.append(line);
                        }
                        showResponse(response.toString());
                    } else {
                        // 请求失败
                        Log.d("haha", "code is "+connection.getResponseCode());
                        //Toast.makeText(MainActivity.this,"连接失败",Toast.LENGTH_LONG).show();
                    }
                   // InputStream in=connection.getInputStream();    //接收返回流
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection!=null){
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }
*/
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(response);
            }
        });
    }
    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.start:
            Intent startIntent=new Intent(this,MyService.class);
            startService(startIntent);   //启动服务
            break;
        case R.id.stop:
            Intent stopIntent=new Intent(this,MyService.class);
            stopService(stopIntent);   //关闭服务
            break;
        case R.id.bind:
            Intent bindIntent=new Intent(this,MyService.class);
            bindService(bindIntent,connection,BIND_AUTO_CREATE);   //绑定服务
            break;
        case R.id.unBind:
            unbindService(connection);   //解绑服务
            break;
            default:
                break;
         }
    }

}
