package rpcfw.core;

import lombok.*;

import java.util.List;

/**
 * Created by fuwei on 2021/8/3.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    int id;
    String name;
    List<String> list;
}
