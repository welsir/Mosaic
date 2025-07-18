package io.github.tml.mosaic.slot;

import io.github.tml.mosaic.core.tools.guid.DotNotationId;
import io.github.tml.mosaic.core.tools.guid.GUID;
import io.github.tml.mosaic.core.tools.guid.UniqueEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.stream.Stream;

import static io.github.tml.mosaic.core.CubeConstant.DEFAULT_CONFIG_ID_VALUE;
import static io.github.tml.mosaic.core.CubeConstant.DEFAULT_RETURN_NAME;

/**
 * 放置Cube的槽，用于引入Cube插件来进行部署和使用
 */
public class Slot extends UniqueEntity {

    @Getter
    private SetupCubeInfo setupCubeInfo;

    public Slot(String slotName) {
        super(new DotNotationId(slotName));
    }

    public Slot(DotNotationId id) {
        super(id);
    }

    public boolean Setup(SetupCubeInfo setupCubeInfo) {
        if(SetupCubeInfo.reliabilityVerify(setupCubeInfo)){
            this.setupCubeInfo = setupCubeInfo;
            return true;
        }
        return false;
    }

    public boolean UnSetup() {
        this.setupCubeInfo = null;
        return true;
    }

    /**
     * 安装的Cube信息
     */
    @NoArgsConstructor
    public static class SetupCubeInfo {

        @Getter
        @Setter
        // 方块唯一Id
        private GUID cubeId;

        // 调用的拓展包Id
        @Getter
        @Setter
        private GUID exPackageId;

        // 调用的拓展点Id
        @Getter
        @Setter
        private GUID exPointId;

        // 需要的返回名称
        @Getter
        @Setter
        private String resName = DEFAULT_RETURN_NAME;

        // 安装配置项
        @Getter
        @Setter
        private String configId = DEFAULT_CONFIG_ID_VALUE;

        /**
         * 可靠性校验，校验SetupCubeInfo是否可用
         */
        public static boolean reliabilityVerify(SetupCubeInfo setupCubeInfo){
            return Objects.nonNull(setupCubeInfo) && Stream.of(setupCubeInfo.getCubeId()).allMatch(Objects::nonNull);
        }
    }

}
