package rpcfw.core;

import lombok.*;

@Data
@AllArgsConstructor
public class RpcResponse {
    private Object result;
    private boolean status;
    private Exception exception;

    public RpcResponse(Object result) {
        this.result = result;
        this.status = true;
    }
}
