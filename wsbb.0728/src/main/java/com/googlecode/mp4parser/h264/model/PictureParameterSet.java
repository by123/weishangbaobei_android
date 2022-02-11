package com.googlecode.mp4parser.h264.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class PictureParameterSet extends BitstreamElement {
    public boolean bottom_field_pic_order_in_frame_present_flag;
    public int[] bottom_right;
    public int chroma_qp_index_offset;
    public boolean constrained_intra_pred_flag;
    public boolean deblocking_filter_control_present_flag;
    public boolean entropy_coding_mode_flag;
    public PPSExt extended;
    public int num_ref_idx_l0_active_minus1;
    public int num_ref_idx_l1_active_minus1;
    public int num_slice_groups_minus1;
    public int pic_init_qp_minus26;
    public int pic_init_qs_minus26;
    public int pic_parameter_set_id;
    public boolean redundant_pic_cnt_present_flag;
    public int[] run_length_minus1;
    public int seq_parameter_set_id;
    public boolean slice_group_change_direction_flag;
    public int slice_group_change_rate_minus1;
    public int[] slice_group_id;
    public int slice_group_map_type;
    public int[] top_left;
    public int weighted_bipred_idc;
    public boolean weighted_pred_flag;

    public static class PPSExt {
        public boolean[] pic_scaling_list_present_flag;
        public ScalingMatrix scalindMatrix = new ScalingMatrix();
        public int second_chroma_qp_index_offset;
        public boolean transform_8x8_mode_flag;

        public String toString() {
            return "PPSExt{transform_8x8_mode_flag=" + this.transform_8x8_mode_flag + ", scalindMatrix=" + this.scalindMatrix + ", second_chroma_qp_index_offset=" + this.second_chroma_qp_index_offset + ", pic_scaling_list_present_flag=" + this.pic_scaling_list_present_flag + '}';
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public static PictureParameterSet read(InputStream inputStream) throws IOException {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:632)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }

    public static PictureParameterSet read(byte[] bArr) throws IOException {
        return read((InputStream) new ByteArrayInputStream(bArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PictureParameterSet pictureParameterSet = (PictureParameterSet) obj;
        if (!Arrays.equals(this.bottom_right, pictureParameterSet.bottom_right)) {
            return false;
        }
        if (this.chroma_qp_index_offset != pictureParameterSet.chroma_qp_index_offset) {
            return false;
        }
        if (this.constrained_intra_pred_flag != pictureParameterSet.constrained_intra_pred_flag) {
            return false;
        }
        if (this.deblocking_filter_control_present_flag != pictureParameterSet.deblocking_filter_control_present_flag) {
            return false;
        }
        if (this.entropy_coding_mode_flag != pictureParameterSet.entropy_coding_mode_flag) {
            return false;
        }
        if (this.extended == null) {
            if (pictureParameterSet.extended != null) {
                return false;
            }
        } else if (!this.extended.equals(pictureParameterSet.extended)) {
            return false;
        }
        if (this.num_ref_idx_l0_active_minus1 != pictureParameterSet.num_ref_idx_l0_active_minus1) {
            return false;
        }
        if (this.num_ref_idx_l1_active_minus1 != pictureParameterSet.num_ref_idx_l1_active_minus1) {
            return false;
        }
        if (this.num_slice_groups_minus1 != pictureParameterSet.num_slice_groups_minus1) {
            return false;
        }
        if (this.pic_init_qp_minus26 != pictureParameterSet.pic_init_qp_minus26) {
            return false;
        }
        if (this.pic_init_qs_minus26 != pictureParameterSet.pic_init_qs_minus26) {
            return false;
        }
        if (this.bottom_field_pic_order_in_frame_present_flag != pictureParameterSet.bottom_field_pic_order_in_frame_present_flag) {
            return false;
        }
        if (this.pic_parameter_set_id != pictureParameterSet.pic_parameter_set_id) {
            return false;
        }
        if (this.redundant_pic_cnt_present_flag != pictureParameterSet.redundant_pic_cnt_present_flag) {
            return false;
        }
        if (!Arrays.equals(this.run_length_minus1, pictureParameterSet.run_length_minus1)) {
            return false;
        }
        if (this.seq_parameter_set_id != pictureParameterSet.seq_parameter_set_id) {
            return false;
        }
        if (this.slice_group_change_direction_flag != pictureParameterSet.slice_group_change_direction_flag) {
            return false;
        }
        if (this.slice_group_change_rate_minus1 != pictureParameterSet.slice_group_change_rate_minus1) {
            return false;
        }
        if (!Arrays.equals(this.slice_group_id, pictureParameterSet.slice_group_id)) {
            return false;
        }
        if (this.slice_group_map_type != pictureParameterSet.slice_group_map_type) {
            return false;
        }
        if (!Arrays.equals(this.top_left, pictureParameterSet.top_left)) {
            return false;
        }
        if (this.weighted_bipred_idc != pictureParameterSet.weighted_bipred_idc) {
            return false;
        }
        return this.weighted_pred_flag == pictureParameterSet.weighted_pred_flag;
    }

    public int hashCode() {
        int hashCode = Arrays.hashCode(this.bottom_right);
        int i = this.chroma_qp_index_offset;
        int i2 = 1237;
        int i3 = this.constrained_intra_pred_flag ? 1231 : 1237;
        int i4 = this.deblocking_filter_control_present_flag ? 1231 : 1237;
        int i5 = this.entropy_coding_mode_flag ? 1231 : 1237;
        int hashCode2 = this.extended == null ? 0 : this.extended.hashCode();
        int i6 = this.num_ref_idx_l0_active_minus1;
        int i7 = this.num_ref_idx_l1_active_minus1;
        int i8 = this.num_slice_groups_minus1;
        int i9 = this.pic_init_qp_minus26;
        int i10 = this.pic_init_qs_minus26;
        int i11 = this.bottom_field_pic_order_in_frame_present_flag ? 1231 : 1237;
        int i12 = this.pic_parameter_set_id;
        int i13 = this.redundant_pic_cnt_present_flag ? 1231 : 1237;
        int hashCode3 = Arrays.hashCode(this.run_length_minus1);
        int i14 = this.seq_parameter_set_id;
        int i15 = this.slice_group_change_direction_flag ? 1231 : 1237;
        int i16 = this.slice_group_change_rate_minus1;
        int hashCode4 = Arrays.hashCode(this.slice_group_id);
        int i17 = this.slice_group_map_type;
        int hashCode5 = Arrays.hashCode(this.top_left);
        int i18 = this.weighted_bipred_idc;
        if (this.weighted_pred_flag) {
            i2 = 1231;
        }
        return ((((((((((((((((((((((((((((((((((((((((i3 + ((((hashCode + 31) * 31) + i) * 31)) * 31) + i4) * 31) + i5) * 31) + hashCode2) * 31) + i6) * 31) + i7) * 31) + i8) * 31) + i9) * 31) + i10) * 31) + i11) * 31) + i12) * 31) + i13) * 31) + hashCode3) * 31) + i14) * 31) + i15) * 31) + i16) * 31) + hashCode4) * 31) + i17) * 31) + hashCode5) * 31) + i18) * 31) + i2;
    }

    public String toString() {
        return "PictureParameterSet{\n       entropy_coding_mode_flag=" + this.entropy_coding_mode_flag + ",\n       num_ref_idx_l0_active_minus1=" + this.num_ref_idx_l0_active_minus1 + ",\n       num_ref_idx_l1_active_minus1=" + this.num_ref_idx_l1_active_minus1 + ",\n       slice_group_change_rate_minus1=" + this.slice_group_change_rate_minus1 + ",\n       pic_parameter_set_id=" + this.pic_parameter_set_id + ",\n       seq_parameter_set_id=" + this.seq_parameter_set_id + ",\n       pic_order_present_flag=" + this.bottom_field_pic_order_in_frame_present_flag + ",\n       num_slice_groups_minus1=" + this.num_slice_groups_minus1 + ",\n       slice_group_map_type=" + this.slice_group_map_type + ",\n       weighted_pred_flag=" + this.weighted_pred_flag + ",\n       weighted_bipred_idc=" + this.weighted_bipred_idc + ",\n       pic_init_qp_minus26=" + this.pic_init_qp_minus26 + ",\n       pic_init_qs_minus26=" + this.pic_init_qs_minus26 + ",\n       chroma_qp_index_offset=" + this.chroma_qp_index_offset + ",\n       deblocking_filter_control_present_flag=" + this.deblocking_filter_control_present_flag + ",\n       constrained_intra_pred_flag=" + this.constrained_intra_pred_flag + ",\n       redundant_pic_cnt_present_flag=" + this.redundant_pic_cnt_present_flag + ",\n       top_left=" + this.top_left + ",\n       bottom_right=" + this.bottom_right + ",\n       run_length_minus1=" + this.run_length_minus1 + ",\n       slice_group_change_direction_flag=" + this.slice_group_change_direction_flag + ",\n       slice_group_id=" + this.slice_group_id + ",\n       extended=" + this.extended + '}';
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public void write(OutputStream outputStream) throws IOException {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:632)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
}
