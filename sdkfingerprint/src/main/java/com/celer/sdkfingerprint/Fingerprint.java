package com.celer.sdkfingerprint;

import android.content.Context;
import android.util.Log;
import com.threatmetrix.TrustDefender.Config;
import com.threatmetrix.TrustDefender.EndNotifier;
import com.threatmetrix.TrustDefender.Profile;
import com.threatmetrix.TrustDefender.ProfilingOptions;
import com.threatmetrix.TrustDefender.TrustDefender;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Fingerprint {


    private Callback callback;
    private String fingerPrint;

    public void generateFingerprint(final Context context, final Boolean isRelease) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                fingerPrint = String.valueOf(new Date().getTime());
                Log.e("FINGER", "finger:" + fingerPrint);
                ProfilingOptions profilingOptions = new ProfilingOptions();
                profilingOptions.setSessionID("redeceler_dm" + fingerPrint);

                Config config = new Config().setOrgId(((isRelease) ? "k8vif92e" : "1snn5n9w"))
                        .setFPServer("h.online-metrix.net")
                        .setUseOKHTTP(true)
                        .setTimeout(20, TimeUnit.SECONDS)
                        .setContext(context);
                TrustDefender instance = TrustDefender.getInstance();

                instance.init(config);
                instance.doProfileRequest(profilingOptions, new Complete());

            }
        }).start();
    }

    public void generateFingerprint(Context context, final Boolean isRelease, Callback callback) {
        this.callback = callback;
        generateFingerprint(context, isRelease);
    }

    class Complete implements EndNotifier {
        @Override
        public void complete(Profile.Result result) {
            String desc = result.getStatus().getDesc();
            if (desc.equals("")) {
                callback.onFingerprint(null);
            } else {
                TrustDefender.getInstance().doPackageScan();
                if (callback != null) {
                    callback.onFingerprint(fingerPrint);
                }
            }


        }
    }

    public interface Callback {
        void onFingerprint(String fingerprint);
    }

}



