package com.wx.assistants.service;

import android.content.Context;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.BankCardParams;
import com.baidu.ocr.sdk.model.BankCardResult;
import com.baidu.ocr.sdk.model.GeneralBasicParams;
import com.baidu.ocr.sdk.model.GeneralParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.OcrRequestParams;
import com.baidu.ocr.sdk.model.OcrResponseResult;
import com.baidu.ocr.sdk.model.Word;
import com.baidu.ocr.sdk.model.WordSimple;
import java.io.File;

public class RecognizeService {

    public interface ServiceListener {
        void onResult(String str);
    }

    public static void recAccurate(Context context, String str, final ServiceListener serviceListener) {
        GeneralParams generalParams = new GeneralParams();
        generalParams.setDetectDirection(true);
        generalParams.setVertexesLocation(true);
        generalParams.setRecognizeGranularity("small");
        generalParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeAccurate(generalParams, new OnResultListener<GeneralResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(GeneralResult generalResult) {
                StringBuilder sb = new StringBuilder();
                for (Word words : generalResult.getWordList()) {
                    sb.append(words.getWords());
                    sb.append("\n");
                }
                serviceListener.onResult(generalResult.getJsonRes());
            }
        });
    }

    public static void recAccurateBasic(Context context, String str, final ServiceListener serviceListener) {
        GeneralParams generalParams = new GeneralParams();
        generalParams.setDetectDirection(true);
        generalParams.setVertexesLocation(true);
        generalParams.setRecognizeGranularity("small");
        generalParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeAccurateBasic(generalParams, new OnResultListener<GeneralResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(GeneralResult generalResult) {
                StringBuilder sb = new StringBuilder();
                for (WordSimple words : generalResult.getWordList()) {
                    sb.append(words.getWords());
                    sb.append("\n");
                }
                serviceListener.onResult(generalResult.getJsonRes());
            }
        });
    }

    public static void recBankCard(Context context, String str, final ServiceListener serviceListener) {
        BankCardParams bankCardParams = new BankCardParams();
        bankCardParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeBankCard(bankCardParams, new OnResultListener<BankCardResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(BankCardResult bankCardResult) {
                serviceListener.onResult(String.format("卡号：%s\n类型：%s\n发卡行：%s", new Object[]{bankCardResult.getBankCardNumber(), bankCardResult.getBankCardType().name(), bankCardResult.getBankName()}));
            }
        });
    }

    public static void recBusinessCard(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeBusinessCard(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recBusinessLicense(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeBusinessLicense(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recCustom(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.putParam("templateSign", "");
        ocrRequestParams.putParam("classifierId", 0);
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeCustom(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recDrivingLicense(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeDrivingLicense(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recGeneral(Context context, String str, final ServiceListener serviceListener) {
        GeneralParams generalParams = new GeneralParams();
        generalParams.setDetectDirection(true);
        generalParams.setVertexesLocation(true);
        generalParams.setRecognizeGranularity("small");
        generalParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeGeneral(generalParams, new OnResultListener<GeneralResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(GeneralResult generalResult) {
                StringBuilder sb = new StringBuilder();
                for (Word words : generalResult.getWordList()) {
                    sb.append(words.getWords());
                    sb.append("\n");
                }
                serviceListener.onResult(generalResult.getJsonRes());
            }
        });
    }

    public static void recGeneralBasic(Context context, String str, final ServiceListener serviceListener) {
        GeneralBasicParams generalBasicParams = new GeneralBasicParams();
        generalBasicParams.setDetectDirection(true);
        generalBasicParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeGeneralBasic(generalBasicParams, new OnResultListener<GeneralResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(GeneralResult generalResult) {
                StringBuilder sb = new StringBuilder();
                for (WordSimple words : generalResult.getWordList()) {
                    sb.append(words.getWords());
                    sb.append("\n");
                }
                serviceListener.onResult(generalResult.getJsonRes());
            }
        });
    }

    public static void recGeneralEnhanced(Context context, String str, final ServiceListener serviceListener) {
        GeneralBasicParams generalBasicParams = new GeneralBasicParams();
        generalBasicParams.setDetectDirection(true);
        generalBasicParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeGeneralEnhanced(generalBasicParams, new OnResultListener<GeneralResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(GeneralResult generalResult) {
                StringBuilder sb = new StringBuilder();
                for (WordSimple words : generalResult.getWordList()) {
                    sb.append(words.getWords());
                    sb.append("\n");
                }
                serviceListener.onResult(generalResult.getJsonRes());
            }
        });
    }

    public static void recHandwriting(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeHandwriting(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recLicensePlate(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeLicensePlate(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recLottery(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeLottery(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recNumbers(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeNumbers(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recPassport(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizePassport(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recQrcode(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeQrcode(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recReceipt(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        ocrRequestParams.putParam("detect_direction", "true");
        OCR.getInstance(context).recognizeReceipt(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recVatInvoice(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeVatInvoice(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recVehicleLicense(Context context, String str, final ServiceListener serviceListener) {
        OcrRequestParams ocrRequestParams = new OcrRequestParams();
        ocrRequestParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeVehicleLicense(ocrRequestParams, new OnResultListener<OcrResponseResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(OcrResponseResult ocrResponseResult) {
                serviceListener.onResult(ocrResponseResult.getJsonRes());
            }
        });
    }

    public static void recWebimage(Context context, String str, final ServiceListener serviceListener) {
        GeneralBasicParams generalBasicParams = new GeneralBasicParams();
        generalBasicParams.setDetectDirection(true);
        generalBasicParams.setImageFile(new File(str));
        OCR.getInstance(context).recognizeWebimage(generalBasicParams, new OnResultListener<GeneralResult>() {
            public void onError(OCRError oCRError) {
                serviceListener.onResult(oCRError.getMessage());
            }

            public void onResult(GeneralResult generalResult) {
                StringBuilder sb = new StringBuilder();
                for (WordSimple words : generalResult.getWordList()) {
                    sb.append(words.getWords());
                    sb.append("\n");
                }
                serviceListener.onResult(generalResult.getJsonRes());
            }
        });
    }
}
