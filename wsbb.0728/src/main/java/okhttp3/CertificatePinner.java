package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

public final class CertificatePinner {
    public static final CertificatePinner DEFAULT = new Builder().build();
    @Nullable
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    CertificatePinner(Set<Pin> set, @Nullable CertificateChainCleaner certificateChainCleaner2) {
        this.pins = set;
        this.certificateChainCleaner = certificateChainCleaner2;
    }

    public static String pin(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + sha256((X509Certificate) certificate).base64();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    static ByteString sha1(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }

    static ByteString sha256(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }

    public void check(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List<Pin> findMatchingPins = findMatchingPins(str);
        if (!findMatchingPins.isEmpty()) {
            if (this.certificateChainCleaner != null) {
                list = this.certificateChainCleaner.clean(list, str);
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                X509Certificate x509Certificate = (X509Certificate) list.get(i);
                int size2 = findMatchingPins.size();
                ByteString byteString = null;
                int i2 = 0;
                ByteString byteString2 = null;
                while (i2 < size2) {
                    Pin pin = findMatchingPins.get(i2);
                    if (pin.hashAlgorithm.equals("sha256/")) {
                        if (byteString == null) {
                            byteString = sha256(x509Certificate);
                        }
                        if (pin.hash.equals(byteString)) {
                            return;
                        }
                    } else if (pin.hashAlgorithm.equals("sha1/")) {
                        if (byteString2 == null) {
                            byteString2 = sha1(x509Certificate);
                        }
                        if (pin.hash.equals(byteString2)) {
                            return;
                        }
                    } else {
                        throw new AssertionError("unsupported hashAlgorithm: " + pin.hashAlgorithm);
                    }
                    i2++;
                    byteString = byteString;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Certificate pinning failure!");
            sb.append("\n  Peer certificate chain:");
            int size3 = list.size();
            for (int i3 = 0; i3 < size3; i3++) {
                X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
                sb.append("\n    ");
                sb.append(pin(x509Certificate2));
                sb.append(": ");
                sb.append(x509Certificate2.getSubjectDN().getName());
            }
            sb.append("\n  Pinned certificates for ");
            sb.append(str);
            sb.append(":");
            int size4 = findMatchingPins.size();
            for (int i4 = 0; i4 < size4; i4++) {
                sb.append("\n    ");
                sb.append(findMatchingPins.get(i4));
            }
            throw new SSLPeerUnverifiedException(sb.toString());
        }
    }

    public void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        check(str, (List<Certificate>) Arrays.asList(certificateArr));
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            return Util.equal(this.certificateChainCleaner, certificatePinner.certificateChainCleaner) && this.pins.equals(certificatePinner.pins);
        }
    }

    /* access modifiers changed from: package-private */
    public List<Pin> findMatchingPins(String str) {
        List<Pin> emptyList = Collections.emptyList();
        List<Pin> list = emptyList;
        for (Pin next : this.pins) {
            if (next.matches(str)) {
                if (list.isEmpty()) {
                    list = new ArrayList<>();
                }
                list.add(next);
            }
        }
        return list;
    }

    public int hashCode() {
        return ((this.certificateChainCleaner != null ? this.certificateChainCleaner.hashCode() : 0) * 31) + this.pins.hashCode();
    }

    /* access modifiers changed from: package-private */
    public CertificatePinner withCertificateChainCleaner(@Nullable CertificateChainCleaner certificateChainCleaner2) {
        return Util.equal(this.certificateChainCleaner, certificateChainCleaner2) ? this : new CertificatePinner(this.pins, certificateChainCleaner2);
    }
}
