package com.shield.chaosshield.common;

import lombok.Getter;

@Getter
public enum State {

    RUNNING(1),
    NOT_RUNNING(0);


    int state;

    State(int state) {
        this.state = state;
    }

}
