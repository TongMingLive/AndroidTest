// IMyName.aidl
package com.example.tong.test1;

// Declare any non-default types here with import statements

interface IMyName {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    void testShow();
}
