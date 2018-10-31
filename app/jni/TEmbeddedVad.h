#ifndef TVAD_TEMBEDDEDVAD_H
#define TVAD_TEMBEDDEDVAD_H
#ifdef __cplusplus
extern "C" {
#endif

/**
 * create a embed instance.
 * @param deploy_dir the model dir
 * @return the instance address; 0:fail.
 */
long long TEmbeddedVad_create(const char* deploy_dir);

/**
 * create a embed instance.
 * @param deploy_dir the model dir
 * @model_name_type the model name type
 * @err_code
 * @return the instance address; 0:fail.
 */
long long TEmbeddedVad_createEx(const char* deploy_dir, int model_name_type, int *err_code);


/**
 * input voice data to embed reco
 * @param handle
 * @param input
 * @param inLen
 * @return
 *    0: error;
 *    1: ok;
 *    2: speech;
 *    3: silence;
 */
int TEmbeddedVad_charInputVoiceData(long long handle, const char* input, int inLen);
/**
 * cancel the embed reco
 * @param handle
 * you should call this after one recording !!!!!
 */
int TEmbeddedVad_cancel(long long handle);

/**
 * delete embed instance
 * @param handle
 * @return 0
 */
int TEmbeddedVad_delete(long long handle);

/**
 * set open or close log print. default log is closed.
 * @param handle
 * @param flag true: open log; false: close log
 * @return 0
 */
int TEmbeddedVad_open_log(long long handle, bool flag);

/**
 * only for server test
 */
int TEmbeddedVad_set_file(long long handle, const char *label_file, const char *boundry_file, const char *utt);

/**
 * only for server test
 */
int TEmbeddedVad_get_label_boundry_vec(long long handle);
int TEmbeddedVad_write_result(long long handle);
int TEmbeddedVad_get_result(long long handle);
void TEmbeddedVad_set_input_finished(long long handle, const bool &input_finished);


/**
 * decide cached-wav's duration.
 * @param handle
 * @return:
 *    -1    error;
 *    other ok;
 */
float TEmbeddedVad_get_cache_seconds(long long handle);

int TEmbeddedVad_get_cache_mseconds(long long handle);

/**
 * return vad version
 * "lib_aaaaaaa,mdl_bbbbbbb"
 */
const char *TEmbeddedVad_get_version(long long handle);

/*
 * you should call this after TEmbeddedVad_create
 * return -1 : params are illegal
 *         0 : success
 */
int TEmbeddedVad_set_vad_params(long long handle, int sil2sph_ms, int sph2sil_ms);


#ifdef __cplusplus
}
#endif
#endif //TVAD_TEMBEDDEDVad_H
