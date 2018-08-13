package xmlProcess;

import org.dom4j.DocumentException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface XmlProcessorList {

    /**
     * 将传输的信息转成XML文件
     * @param information :参数信息，包括数据集，模型参数等
     * @param filepath: 生成的xml文件存放的位置
     * @return xml文件
     */
    boolean generateXmlFile(List<Map> information, String filepath) throws URISyntaxException, InterruptedException, IOException;

    /**
     * 将传输的XML文件解析成List或Map
     * @param xmlFile: xml文件
     * @return 由xml文件解析的List或Map
     */
    List analyzeXmlFile(File xmlFile) throws DocumentException;


}