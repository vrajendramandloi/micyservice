package com.uni.micy.service.queue;

import com.uni.micy.service.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class LinkedBlockingQueue {
    private BlockingQueue<UserDetails> blockingQueue = new LinkedBlockingDeque<>();

    public void addUserDetailToBlockingQueue(UserDetails userDetails) {
        this.blockingQueue.add(userDetails);
    }

    public BlockingQueue<UserDetails> getBlockingQueue() {
        return blockingQueue;
    }
}
