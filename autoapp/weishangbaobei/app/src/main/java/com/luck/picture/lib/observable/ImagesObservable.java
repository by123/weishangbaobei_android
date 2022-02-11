package com.luck.picture.lib.observable;

import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import java.util.ArrayList;
import java.util.List;

public class ImagesObservable implements SubjectListener {
    private static ImagesObservable sObserver;
    private List<LocalMediaFolder> folders = new ArrayList();
    private List<LocalMedia> medias = new ArrayList();
    private List<ObserverListener> observers = new ArrayList();
    private List<LocalMedia> selectedImages = new ArrayList();

    private ImagesObservable() {
    }

    public static ImagesObservable getInstance() {
        if (sObserver == null) {
            synchronized (ImagesObservable.class) {
                if (sObserver == null) {
                    sObserver = new ImagesObservable();
                }
            }
        }
        return sObserver;
    }

    public void saveLocalFolders(List<LocalMediaFolder> list) {
        if (list != null) {
            this.folders = list;
        }
    }

    public void saveLocalMedia(List<LocalMedia> list) {
        this.medias = list;
    }

    public List<LocalMedia> readLocalMedias() {
        if (this.medias == null) {
            this.medias = new ArrayList();
        }
        return this.medias;
    }

    public List<LocalMediaFolder> readLocalFolders() {
        if (this.folders == null) {
            this.folders = new ArrayList();
        }
        return this.folders;
    }

    public List<LocalMedia> readSelectLocalMedias() {
        return this.selectedImages;
    }

    public void clearLocalFolders() {
        if (this.folders != null) {
            this.folders.clear();
        }
    }

    public void clearLocalMedia() {
        if (this.medias != null) {
            this.medias.clear();
        }
    }

    public void clearSelectedLocalMedia() {
        if (this.selectedImages != null) {
            this.selectedImages.clear();
        }
    }

    public void add(ObserverListener observerListener) {
        this.observers.add(observerListener);
    }

    public void remove(ObserverListener observerListener) {
        if (this.observers.contains(observerListener)) {
            this.observers.remove(observerListener);
        }
    }
}
