package com.sensology.framelib;

import com.sensology.framelib.imageloader.ILoader;
import com.sensology.framelib.kit.Kits;
import com.sensology.framelib.router.Router;


public class XConfigure {
    // #log
    public static boolean LOG = BuildConfig.DEBUG;
    public static String LOG_TAG = "XDroid";

    // #cache
    public static String CACHE_SP_NAME = "Cache_sp_SenHome";
    public static String CACHE_DISK_DIR = "cache";

    // #router
    public static int ROUTER_ANIM_ENTER = Router.RES_NONE;
    public static int ROUTER_ANIM_EXIT = Router.RES_NONE;

    // #imageloader
    public static int IL_LOADING_RES = ILoader.Options.RES_NONE;
    public static int IL_ERROR_RES = ILoader.Options.RES_NONE;

    // #dev model
    public static boolean DEV = true;

    /**
     * config log
     *
     * @param log
     * @param logTag
     */
    public static void configLog(boolean log, String logTag) {
        LOG = log;
        if (!Kits.Empty.check(logTag)) {
            LOG_TAG = logTag;
        }
    }

    /**
     * conf cache
     *
     * @param spName
     * @param diskDir
     */
    public static void configCache(String spName, String diskDir) {
        if (!Kits.Empty.check(spName)) {
            CACHE_SP_NAME = spName;
        }
        if (!Kits.Empty.check(diskDir)) {
            CACHE_DISK_DIR = diskDir;
        }
    }

    /**
     * config dev
     *
     * @param isDev
     */
    public static void devMode(boolean isDev) {
        DEV = isDev;
    }

}
