/*
 * Copyright 2014-2021 Real Logic Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.agrona.concurrent;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Benchmark for the {@link UnsafeBuffer#putIntAscii(int, int)} method.
 */
@Fork(3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@State(Scope.Benchmark)
public class UnsafeBufferPutIntAsciiBenchmark
{
    @Param({ "-2147483648", "0", "-9182", "27085146", "-123456789", "2147483647" })
    private int value;

    private final UnsafeBuffer buffer = new UnsafeBuffer(new byte[64]);

    /**
     * Benchmark {@link UnsafeBuffer#putIntAscii(int, int)} method.s
     *
     * @return length in bytes of the written value.
     */
    @Benchmark
    public int benchmark()
    {
        return buffer.putIntAscii(0, value);
    }
}