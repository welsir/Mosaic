package io.github.tml.mosaic.cube.factory.context;

import io.github.tml.mosaic.cube.factory.CubeFactory;
import io.github.tml.mosaic.cube.factory.definition.CubeDefinition;
import io.github.tml.mosaic.core.tools.guid.GUID;
import java.util.List;

/**
 * 描述: Cube上下文接口
 * @author suifeng
 * 日期: 2025/6/7
 */
public interface CubeContext extends CubeFactory {

    void registerCubeDefinition(GUID cubeId, CubeDefinition cubeDefinition);

    void registerAllCubeDefinition(List<CubeDefinition> cubeDefinitionList);
}
