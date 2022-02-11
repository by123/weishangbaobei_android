package com.luck.picture.lib.anim;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OptAnimationLoader {
    private static Animation createAnimationFromXml(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return createAnimationFromXml(context, xmlPullParser, (AnimationSet) null, Xml.asAttributeSet(xmlPullParser));
    }

    private static Animation createAnimationFromXml(Context context, XmlPullParser xmlPullParser, AnimationSet animationSet, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        Animation animation = null;
        while (true) {
            int next = xmlPullParser.next();
            if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    if (name.equals("set")) {
                        AnimationSet animationSet2 = new AnimationSet(context, attributeSet);
                        createAnimationFromXml(context, xmlPullParser, animationSet2, attributeSet);
                        animation = animationSet2;
                    } else if (name.equals("alpha")) {
                        animation = new AlphaAnimation(context, attributeSet);
                    } else if (name.equals("scale")) {
                        animation = new ScaleAnimation(context, attributeSet);
                    } else if (name.equals("rotate")) {
                        animation = new RotateAnimation(context, attributeSet);
                    } else if (name.equals("translate")) {
                        animation = new TranslateAnimation(context, attributeSet);
                    } else {
                        try {
                            animation = (Animation) Class.forName(name).getConstructor(new Class[]{Context.class, AttributeSet.class}).newInstance(new Object[]{context, attributeSet});
                        } catch (Exception e) {
                            throw new RuntimeException("Unknown animation name: " + xmlPullParser.getName() + " error:" + e.getMessage());
                        }
                    }
                    if (animationSet != null) {
                        animationSet.addAnimation(animation);
                    }
                }
            }
        }
        return animation;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:9:0x0014=Splitter:B:9:0x0014, B:17:0x003a=Splitter:B:17:0x003a} */
    public static Animation loadAnimation(Context context, int i) throws Resources.NotFoundException {
        XmlResourceParser xmlResourceParser = null;
        try {
            xmlResourceParser = context.getResources().getAnimation(i);
            try {
                Animation createAnimationFromXml = createAnimationFromXml(context, xmlResourceParser);
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
                return createAnimationFromXml;
            } catch (XmlPullParserException e) {
                e = e;
            } catch (IOException e2) {
                e = e2;
                try {
                    Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                    notFoundException.initCause(e);
                    throw notFoundException;
                } catch (Throwable th) {
                    th = th;
                    if (xmlResourceParser != null) {
                        xmlResourceParser.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (xmlResourceParser != null) {
                }
                throw th;
            }
        } catch (XmlPullParserException e3) {
            e = e3;
        } catch (IOException e4) {
            e = e4;
            Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
            notFoundException2.initCause(e);
            throw notFoundException2;
        }
        Resources.NotFoundException notFoundException3 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
        notFoundException3.initCause(e);
        throw notFoundException3;
    }
}
