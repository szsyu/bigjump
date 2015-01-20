package com.ect.bigjump.test.service;

import com.ect.bigjump.domain.*;

import com.ect.bigjump.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath*:config/bigjump-activiti.xml","classpath*:config/bigjump-core.xml"})
public class TestDocumentService {

	@Autowired
	private DocumentService documentService;

	@Autowired
	private DocumentMasterService documentMasterService;

	@Autowired
	private DocumentDataService documentDataService;

	@Autowired
	private DocumentDataLevelService documentDataLevelService;

	@Autowired
	private DocumentDataImportService documentDataImportService;

	@Autowired
	private DocDataInterfaceService docDataInterfaceService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private DocAssigneeCandidatesService docAssigneeCandidatesService;
	
	@Test
	public void add() throws Exception{
         Document document = new Document();
		document.setDocumentCode("TEST01");
		document.setDocumentName("test01");
		documentService.add(document);

	}

	@Test
	public void addDataLevel() throws Exception{
		DocumentDataLevel documentDataLevel = new DocumentDataLevel();
		documentDataLevel.setDocument(documentService.get(4L));
		documentDataLevel.setLevelName("test");
		documentDataLevel.setIsActive("Y");
		documentDataLevel.setDescription("test120");

		documentDataLevelService.add(documentDataLevel);
	}

	@Test
	public void importData(){
		documentDataImportService.startImporting();
	}

	@Test
	public void getTaskList(){
		Page<DocAssigneeCandidates> docAssigneeCandicatesPage = docAssigneeCandidatesService.queryForPage(1,2L,null,null);
		System.out.println("123");
	}

	@Test
	public void getDocumenetDetail() throws Exception{
		DocumentMaster documentMaster = documentMasterService.get(21L);
		DocumentDetail documentDetail = documentDataService.getDocumentDetail(documentMaster);

	}

	@Test
	public void getTreeStr(){
		System.out.print(organizationService.getOrganizationTree(3L));
	}

	@Test
	public void importTestingData() throws Exception{
		Document document = documentService.getByCode("TEST01");
		for(int i = 1;i<100;i++){
			DocDataInterface docDataInterface0 = new DocDataInterface();
			docDataInterface0.setData1("PR" + (150000+i));
			docDataInterface0.setData2("Shawn Yu");
			docDataInterface0.setData3("TEST" + i);
			docDataInterface0.setData4("Shawn Company");
			docDataInterface0.setData5("1019");
			docDataInterface0.setData6((19950+i)+"");
			docDataInterface0.setDataLevel(0);
			docDataInterface0.setProcessStatus("N");
			docDataInterface0.setSourcePKId((100 + i) + "");
			docDataInterface0.setDocumentNumber("PR" + (150000+i));
			docDataInterface0.setDocument(document);

			docDataInterfaceService.add(docDataInterface0);

			DocDataInterface docDataInterface1 = new DocDataInterface();
			docDataInterface1.setData1("1");
			docDataInterface1.setData2("TEST123");
			docDataInterface1.setData3("Laptop");
			docDataInterface1.setData4("25000");
			docDataInterface1.setData5("1");
			docDataInterface1.setData6((19950 + i) + "");
			docDataInterface1.setDataLevel(1);
			docDataInterface1.setProcessStatus("N");
			docDataInterface1.setSourcePKId((100 + i) + "");
			docDataInterface1.setSourceFKId((100 + i) + "");
			docDataInterface1.setDocumentNumber("PR" + (150000+i));
			docDataInterface1.setDocument(document);
			docDataInterfaceService.add(docDataInterface1);
		}
	}
}
