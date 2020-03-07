package com.imzhizi.algs.grammar;

import com.imzhizi.algs.base.Student1;
import com.imzhizi.algs.base.Student2;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class 反射 {
    public void 异类反射浅拷贝(Object srcObject, Object desObject) throws IllegalAccessException, NoSuchFieldException {
        Assert.assertNotNull("Source must not be null", srcObject);
        Assert.assertNotNull("Destination must not be null", desObject);

        Class srcClazz = srcObject.getClass();
        Set<String> srcFields = Arrays.stream(srcClazz.getDeclaredFields()).map(Field::getName).collect(Collectors.toSet());

        Class desClazz = desObject.getClass();
        Field[] desFields = desClazz.getDeclaredFields();

        for (Field df : desFields) {
            if (srcFields.contains(df.getName())) {
                Field sf = srcClazz.getDeclaredField(df.getName());
                if (sf.getType() == df.getType()) {
                    sf.setAccessible(true);
                    df.setAccessible(true);
                    df.set(desObject, sf.get(srcObject));
                }
            }
        }
    }

    @Test
    public void testReflect() throws NoSuchFieldException, IllegalAccessException {
        Student1 srcObject = new Student1("zhizi", "qwer1234", "male");
        Student2 desObject = new Student2();
        异类反射浅拷贝(srcObject, desObject);
        System.out.println(srcObject);
        System.out.println(desObject);
    }

}
