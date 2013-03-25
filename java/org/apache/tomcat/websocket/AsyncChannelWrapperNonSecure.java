/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tomcat.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncChannelWrapperNonSecure implements AsyncChannelWrapper {

    private final AsynchronousSocketChannel socketChannel;

    public AsyncChannelWrapperNonSecure(
            AsynchronousSocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public Future<Integer> read(ByteBuffer dst) {
        return socketChannel.read(dst);
    }

    @Override
    public <A> void read(ByteBuffer dst, A attachment,
            CompletionHandler<Integer,? super A> handler) {
        socketChannel.read(dst, attachment, handler);
    }

    @Override
    public Future<Integer> write(ByteBuffer src) {
        return socketChannel.write(src);
    }

    @Override
    public <A> void write(ByteBuffer[] srcs, int offset, int length,
            long timeout, TimeUnit unit, A attachment,
            CompletionHandler<Long,? super A> handler) {
        socketChannel.write(
                srcs, offset, length, timeout, unit, attachment, handler);
    }

    @Override
    public void close() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            // Ignore
        }
    }
}