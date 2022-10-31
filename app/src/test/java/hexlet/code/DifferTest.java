package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

@DisplayName("Тестируем Differ")
class DifferTest {
    private String expectedPatch =
            "{\n" + "  - follow: false\n" + "    host: hexlet.io\n" + "  - proxy: 123.234.53.22\n" + "  - timeout: 50\n"
                    + "  + timeout: 20\n" + "  + verbose: true\n" + "}";
    private Path path1;
    private Path path2;

    @BeforeEach
    void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();

        String resourceName1 = "fileY1.yml";
        File file1 = new File(classLoader.getResource(resourceName1).getFile());
        path1 = Path.of(file1.getPath());

        String resourceName2 = "fileY2.yml";
        File file2 = new File(classLoader.getResource(resourceName2).getFile());
        path2 = Path.of(file2.getPath());
    }

    @Test
    @DisplayName("generate сравнивает результат с правильным файлом")
    void shouldCorrectGeneratePatch() {
        try {
            var actualPatch = Differ.generate(path1, path2);
            Assertions.assertEquals(expectedPatch, actualPatch);
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("generate должен бросить исключение если файла нет")
    void shouldThrowExceptionFileNotExist() {
        try {
            Differ.generate(Path.of("filetNotExisted.json"), path2);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
}
