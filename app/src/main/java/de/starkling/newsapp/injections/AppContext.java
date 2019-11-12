package de.starkling.newsapp.injections;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Zohaib Akram on 2019-08-19
 * Copyright © 2019 Starkling. All rights reserved.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AppContext {
}