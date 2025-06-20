package io.github.tml.mosaic.cube.factory.support;

import io.github.tml.mosaic.core.execption.CubeException;
import io.github.tml.mosaic.core.tools.guid.GUID;
import io.github.tml.mosaic.cube.*;
import io.github.tml.mosaic.cube.factory.definition.CubeDefinition;
import io.github.tml.mosaic.cube.factory.config.InstantiationStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述: 第一步：用于实例化Cube的类
 *
 * @author suifeng
 * 日期: 2025/6/6
 */
@Slf4j
public abstract class AbstractAutowireCapableCubeFactory extends AbstractCubeFactory {

    private final InstantiationStrategy instantiationStrategy = new DefaultInstantiationStrategy();

    @Override
    protected Cube createCube(GUID cubeId, CubeDefinition cubeDefinition, Object[] args) throws CubeException {
        Cube cube = null;
        try {
            cube = instantiationStrategy.instantiate(cubeDefinition, cubeId, args);
            // 给 Cube 填充属性
            applyPropertyValues(cube, cubeDefinition);
            // 执行 Cube 的初始化方法
            cube = initializeCube(cube, cubeDefinition, args);
        } catch (Exception e) {
            throw new CubeException("Instantiation of cube failed", e);
        }
        // 存放到单例池
        addSingleton(cubeId, cube);
        return cube;
    }

    protected abstract Cube initializeCube(Cube cube, CubeDefinition cubeDefinition, Object[] args) throws CubeException;
    protected abstract void applyPropertyValues(Cube cube, CubeDefinition cubeDefinition);
}
