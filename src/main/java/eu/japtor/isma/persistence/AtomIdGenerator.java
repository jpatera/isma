/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.japtor.isma.persistence;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Honza
 * 
 * Unique string-number generator (suitable for demo)
 * Shorter strings than with standard java.util UUID generator
 * To be used for domain specific codes (aka IDs)
 */
public class AtomIdGenerator {

    private static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public static String nextId() {
        long next = id.incrementAndGet();
        return String.valueOf(next);
    }
    
}

 
    