package com.tencent.bugly.crashreport.biz;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.stub.StubApp;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.biz.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.List;

public class b {
    public static a a = null;
    private static boolean b = false;
    /* access modifiers changed from: private */
    public static int c = 10;
    /* access modifiers changed from: private */
    public static long d = 300000;
    /* access modifiers changed from: private */
    public static long e = 30000;
    /* access modifiers changed from: private */
    public static long f = 0;
    /* access modifiers changed from: private */
    public static int g = 0;
    /* access modifiers changed from: private */
    public static long h = 0;
    /* access modifiers changed from: private */
    public static long i = 0;
    /* access modifiers changed from: private */
    public static long j = 0;
    private static Application.ActivityLifecycleCallbacks k = null;
    /* access modifiers changed from: private */
    public static Class<?> l = null;
    /* access modifiers changed from: private */
    public static boolean m = true;

    static /* synthetic */ String a(String str, String str2) {
        return z.a() + "  " + str + "  " + str2 + "\n";
    }

    public static void a() {
        if (a != null) {
            a.a(2, false, 0);
        }
    }

    public static void a(long j2) {
        if (j2 < 0) {
            j2 = a.a().c().q;
        }
        f = j2;
    }

    public static void a(Context context) {
        if (b && context != null) {
            Application application = null;
            if (Build.VERSION.SDK_INT >= 14) {
                if (StubApp.getOrigApplicationContext(context.getApplicationContext()) instanceof Application) {
                    application = (Application) StubApp.getOrigApplicationContext(context.getApplicationContext());
                }
                if (application != null) {
                    try {
                        if (k != null) {
                            application.unregisterActivityLifecycleCallbacks(k);
                        }
                    } catch (Exception e2) {
                        if (!x.a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            b = false;
        }
    }

    public static void a(final Context context, final BuglyStrategy buglyStrategy) {
        long j2;
        if (!b) {
            m = com.tencent.bugly.crashreport.common.info.a.a(context).e;
            a = new a(context, m);
            b = true;
            if (buglyStrategy != null) {
                l = buglyStrategy.getUserInfoActivity();
                j2 = buglyStrategy.getAppReportDelay();
            } else {
                j2 = 0;
            }
            if (j2 <= 0) {
                c(context, buglyStrategy);
            } else {
                w.a().a(new Runnable() {
                    public final void run() {
                        b.c(context, buglyStrategy);
                    }
                }, j2);
            }
        }
    }

    public static void a(StrategyBean strategyBean, boolean z) {
        if (a != null && !z) {
            a aVar = a;
            w a2 = w.a();
            if (a2 != null) {
                a2.a(new Runnable() {
                    public final void run(
/*
Method generation error in method: com.tencent.bugly.crashreport.biz.a.2.run():void, dex: com.example.wx.assistant_dumped_8-dex2jar.jar
                    jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.tencent.bugly.crashreport.biz.a.2.run():void, class status: UNLOADED
                    	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                    	at java.base/java.util.ArrayList.forEach(Unknown Source)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                    	at java.base/java.util.ArrayList.forEach(Unknown Source)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    
*/
                });
            }
        }
        if (strategyBean != null) {
            if (strategyBean.q > 0) {
                e = strategyBean.q;
            }
            if (strategyBean.w > 0) {
                c = strategyBean.w;
            }
            if (strategyBean.x > 0) {
                d = strategyBean.x;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context, BuglyStrategy buglyStrategy) {
        boolean z;
        boolean z2;
        boolean z3;
        if (buglyStrategy != null) {
            z2 = buglyStrategy.recordUserInfoOnceADay();
            z = buglyStrategy.isEnableUserInfo();
        } else {
            z = true;
            z2 = false;
        }
        if (z2) {
            com.tencent.bugly.crashreport.common.info.a a2 = com.tencent.bugly.crashreport.common.info.a.a(context);
            List<UserInfoBean> a3 = a.a(a2.d);
            if (a3 != null) {
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= a3.size()) {
                        break;
                    }
                    UserInfoBean userInfoBean = a3.get(i3);
                    if (userInfoBean.n.equals(a2.k) && userInfoBean.b == 1) {
                        long b2 = z.b();
                        if (b2 <= 0) {
                            break;
                        } else if (userInfoBean.e >= b2) {
                            if (userInfoBean.f <= 0) {
                                a aVar = a;
                                w a4 = w.a();
                                if (a4 != null) {
                                    a4.a(new Runnable() {
                                        /*  JADX ERROR: Method generation error
                                            jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.tencent.bugly.crashreport.biz.a.2.run():void, class status: UNLOADED
                                            	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                                            	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                                            	at java.base/java.util.ArrayList.forEach(Unknown Source)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                                            	at java.base/java.util.ArrayList.forEach(Unknown Source)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                                            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                                            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                                            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                                            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                                            */
                                        /*  JADX ERROR: Method generation error: Method args not loaded: com.tencent.bugly.crashreport.biz.a.2.run():void, class status: UNLOADED
                                            jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.tencent.bugly.crashreport.biz.a.2.run():void, class status: UNLOADED
                                            	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                                            	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                                            	at java.base/java.util.ArrayList.forEach(Unknown Source)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                                            	at java.base/java.util.ArrayList.forEach(Unknown Source)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                                            	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                                            	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                                            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                                            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                                            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                                            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                                            */
                                        public final void run(
/*
Method generation error in method: com.tencent.bugly.crashreport.biz.a.2.run():void, dex: com.example.wx.assistant_dumped_8-dex2jar.jar
                                        jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.tencent.bugly.crashreport.biz.a.2.run():void, class status: UNLOADED
                                        	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                                        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                                        	at java.base/java.util.ArrayList.forEach(Unknown Source)
                                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                                        	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                                        	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                                        	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                                        	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                        	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:205)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                                        	at java.base/java.util.ArrayList.forEach(Unknown Source)
                                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                                        	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                                        	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                                        	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                                        	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                                        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                                        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                                        
*/
                                    });
                                }
                            }
                            z3 = false;
                        }
                    }
                    i2 = i3 + 1;
                }
            }
            z3 = true;
            if (z3) {
                z = false;
            } else {
                return;
            }
        }
        com.tencent.bugly.crashreport.common.info.a b3 = com.tencent.bugly.crashreport.common.info.a.b();
        if (b3 != null) {
            String str = null;
            boolean z4 = false;
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                if (stackTraceElement.getMethodName().equals("onCreate")) {
                    str = stackTraceElement.getClassName();
                }
                if (stackTraceElement.getClassName().equals("android.app.Activity")) {
                    z4 = true;
                }
            }
            if (str == null) {
                str = "unknown";
            } else if (z4) {
                b3.a(true);
            } else {
                str = "background";
            }
            b3.q = str;
        }
        if (z && Build.VERSION.SDK_INT >= 14) {
            Application application = StubApp.getOrigApplicationContext(context.getApplicationContext()) instanceof Application ? (Application) StubApp.getOrigApplicationContext(context.getApplicationContext()) : null;
            if (application != null) {
                try {
                    if (k == null) {
                        k = new Application.ActivityLifecycleCallbacks() {
                            public final void onActivityCreated(Activity activity, Bundle bundle) {
                                String str = "unknown";
                                if (activity != null) {
                                    str = activity.getClass().getName();
                                }
                                if (b.l == null || b.l.getName().equals(str)) {
                                    x.c(">>> %s onCreated <<<", str);
                                    com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
                                    if (b != null) {
                                        b.E.add(b.a(str, "onCreated"));
                                    }
                                }
                            }

                            public final void onActivityDestroyed(Activity activity) {
                                String str = "unknown";
                                if (activity != null) {
                                    str = activity.getClass().getName();
                                }
                                if (b.l == null || b.l.getName().equals(str)) {
                                    x.c(">>> %s onDestroyed <<<", str);
                                    com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
                                    if (b != null) {
                                        b.E.add(b.a(str, "onDestroyed"));
                                    }
                                }
                            }

                            public final void onActivityPaused(Activity activity) {
                                String str = "unknown";
                                if (activity != null) {
                                    str = activity.getClass().getName();
                                }
                                if (b.l == null || b.l.getName().equals(str)) {
                                    x.c(">>> %s onPaused <<<", str);
                                    com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
                                    if (b != null) {
                                        b.E.add(b.a(str, "onPaused"));
                                        b.a(false);
                                        b.s = System.currentTimeMillis();
                                        b.t = b.s - b.r;
                                        long unused = b.h = b.s;
                                        if (b.t < 0) {
                                            b.t = 0;
                                        }
                                        if (activity != null) {
                                            b.q = "background";
                                        } else {
                                            b.q = "unknown";
                                        }
                                    }
                                }
                            }

                            public final void onActivityResumed(Activity activity) {
                                String str = "unknown";
                                if (activity != null) {
                                    str = activity.getClass().getName();
                                }
                                if (b.l == null || b.l.getName().equals(str)) {
                                    x.c(">>> %s onResumed <<<", str);
                                    com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
                                    if (b != null) {
                                        b.E.add(b.a(str, "onResumed"));
                                        b.a(true);
                                        b.q = str;
                                        b.r = System.currentTimeMillis();
                                        b.u = b.r - b.i;
                                        long d = b.r - b.h;
                                        if (d > (b.f > 0 ? b.f : b.e)) {
                                            b.d();
                                            b.g();
                                            x.a("[session] launch app one times (app in background %d seconds and over %d seconds)", Long.valueOf(d / 1000), Long.valueOf(b.e / 1000));
                                            if (b.g % b.c == 0) {
                                                b.a.a(4, b.m, 0);
                                                return;
                                            }
                                            b.a.a(4, false, 0);
                                            long currentTimeMillis = System.currentTimeMillis();
                                            if (currentTimeMillis - b.j > b.d) {
                                                long unused = b.j = currentTimeMillis;
                                                x.a("add a timer to upload hot start user info", new Object[0]);
                                                if (b.m) {
                                                    w.a().a(new a.C0001a((UserInfoBean) null, true), b.d);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                            }

                            public final void onActivityStarted(Activity activity) {
                            }

                            public final void onActivityStopped(Activity activity) {
                            }
                        };
                    }
                    application.registerActivityLifecycleCallbacks(k);
                } catch (Exception e2) {
                    if (!x.a(e2)) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        if (m) {
            i = System.currentTimeMillis();
            a.a(1, false, 0);
            x.a("[session] launch app, new start", new Object[0]);
            a.a();
            w.a().a(new a.c(21600000), 21600000);
        }
    }

    static /* synthetic */ int g() {
        int i2 = g;
        g = i2 + 1;
        return i2;
    }
}
