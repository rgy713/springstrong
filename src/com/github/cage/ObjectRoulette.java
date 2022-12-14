/*
 * Copyright 2011 Király Attila
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cage;

import java.util.Random;

/**
 * An {@link IGenerator} implementation that returns a randomly chosen element
 * from a predefined set of objects when {@link #next()} is called. This class
 * is thread safe.
 * 
 * @param <T>
 *        the type of objects generated by this class
 * 
 * @author akiraly
 */
public class ObjectRoulette<T> implements IGenerator<T> {
    private final T[] candidates;

    private final Random rnd;

    /**
     * Constructor.
     * 
     * @param rnd
     *        random generator to be used, can be null
     * @param candidates
     *        the set of objects to choose from; not null, not empty
     */
    @SuppressWarnings("unchecked")
    public ObjectRoulette(Random rnd, T... candidates) {
        if (candidates == null || candidates.length < 1)
            throw new IllegalArgumentException("No candidates given.");
        this.candidates = candidates;
        this.rnd = rnd != null ? rnd : new Random();
    }

    @Override
    public T next() {
        return candidates[rnd.nextInt(candidates.length)];
    }

    /**
     * @return the set of objects to choose from
     */
    public T[] getCandidates() {
        return candidates;
    }
}
