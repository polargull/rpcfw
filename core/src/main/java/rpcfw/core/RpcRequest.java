package rpcfw.core;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest {
  private String serviceClass;
  private String method;
  private Object[] params;
}
