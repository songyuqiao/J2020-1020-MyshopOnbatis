package com.mobiletrain.mapper;

import com.mobiletrain.domain.Address;

import java.util.List;

public interface AddressMapper {

    int insert(Address address);

    List<Address> queryByUid(String uid);
}

