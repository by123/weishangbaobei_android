package okio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

class Okio$4 extends AsyncTimeout {
    final /* synthetic */ Socket val$socket;

    Okio$4(Socket socket) {
        this.val$socket = socket;
    }

    /* access modifiers changed from: protected */
    public IOException newTimeoutException(@Nullable IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    /* access modifiers changed from: protected */
    public void timedOut() {
        try {
            this.val$socket.close();
        } catch (Exception e) {
            Logger logger = Okio.logger;
            Level level = Level.WARNING;
            logger.log(level, "Failed to close timed out socket " + this.val$socket, e);
        } catch (AssertionError e2) {
            if (Okio.isAndroidGetsocknameError(e2)) {
                Logger logger2 = Okio.logger;
                Level level2 = Level.WARNING;
                logger2.log(level2, "Failed to close timed out socket " + this.val$socket, e2);
                return;
            }
            throw e2;
        }
    }
}
