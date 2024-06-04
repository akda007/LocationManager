package com.locationmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.locationmanager.validator.CpfValidator;

public class CpfValidatorTest {
    @Test
    void validate() {
        assertEquals(true, CpfValidator.validateCpf("889.935.990-33"));
        assertEquals(false, CpfValidator.validateCpf("11.922.990-32"));
        assertEquals(false, CpfValidator.validateCpf("aaaaaa"));
        assertEquals(false, CpfValidator.validateCpf("11111111111"));
        assertEquals(false, CpfValidator.validateCpf("6416151"));
        assertEquals(true, CpfValidator.validateCpf("12213199060"));
        assertEquals(true, CpfValidator.validateCpf("684.937.180-09"));

    }
}
