package com.duocuc.motopapis.util.iface;

import org.springframework.util.LinkedMultiValueMap;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface FlowUtils {

  String createSing(LinkedMultiValueMap<String, String> map) throws NoSuchAlgorithmException;

  List<String> createListKeys(LinkedMultiValueMap<String, String> map);

  String getKey();
}
