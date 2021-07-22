package com.customer.delivX.util;

import android.content.Context;
import android.os.Debug;
import android.util.Log;

import com.BuildConfig;
import com.customer.delivX.data.source.SessionHelper;
import com.threatmetrix.TrustDefender.Config;
import com.threatmetrix.TrustDefender.EndNotifier;
import com.threatmetrix.TrustDefender.Profile;
import com.threatmetrix.TrustDefender.ProfilingOptions;
import com.threatmetrix.TrustDefender.TrustDefender;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class Fingerprint {


    private Callback callback;
    private String fingerPrint;

    public void generateFingerprint(Context context) {
        new Thread(() -> {
            fingerPrint =  String.valueOf(new Date().getTime());
            Log.e("FINGER","finger:" +fingerPrint);
            ProfilingOptions profilingOptions = new ProfilingOptions();
            profilingOptions.setSessionID("redeceler_dm"+fingerPrint);

            Config config = new Config().setOrgId(BuildConfig.DEBUG ? "1snn5n9w" : "k8vif92e")
                    .setFPServer("h.online-metrix.net")
                    .setUseOKHTTP(true)
                    .setTimeout(20, TimeUnit.SECONDS)
                    .setContext(context);
            TrustDefender instance = TrustDefender.getInstance();

            instance.init(config);
            instance.doProfileRequest(profilingOptions, new Complete());

        }).start();
    }

    public void generateFingerprint(Context context, Callback callback) {
        this.callback = callback;
        generateFingerprint(context);
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



