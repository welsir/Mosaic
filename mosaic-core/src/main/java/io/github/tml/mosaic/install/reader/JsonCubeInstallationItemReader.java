package io.github.tml.mosaic.install.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tml.mosaic.core.execption.CubeException;
import io.github.tml.mosaic.cube.factory.io.loader.DefaultResourceLoader;
import io.github.tml.mosaic.cube.factory.io.loader.ResourceLoader;
import io.github.tml.mosaic.cube.factory.io.resource.Resource;
import io.github.tml.mosaic.install.domian.install.InstallationConfig;
import io.github.tml.mosaic.install.reader.core.AbstractCubeInstallationItemReader;
import io.github.tml.mosaic.install.support.ReaderType;

import java.io.IOException;
import java.io.InputStream;

/**
 * 描述: 从Json的配置文件中去加载CubeInfo
 * @author suifeng
 * 日期: 2025/6/7
 */
public class JsonCubeInstallationItemReader extends AbstractCubeInstallationItemReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonCubeInstallationItemReader() {
        super(new DefaultResourceLoader());
    }

    public JsonCubeInstallationItemReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public ReaderType getReaderType() {
        return ReaderType.JSON;
    }

    @Override
    protected InstallationConfig doLoadCubeInstallationItem(Resource resource) throws CubeException {
        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, InstallationConfig.class);
        } catch (IOException e) {
            throw new CubeException("Failed to parse JSON document from " + resource, e);
        }
    }
}
