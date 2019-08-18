package com.ontravelsolutions.kostusev.townforevery.entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public interface BaseEntity<T extends Serializable> {

    T getId();
}
