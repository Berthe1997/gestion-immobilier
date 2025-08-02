package com.news.excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.news.beans.Agence;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans_GT.Client_acheteur;
import com.news.beans_GT.Dossier_client;
import com.news.beans_GT.Paiement_terrain;
import com.news.beans_GT.Terrain;
import com.news.dao_GT.Client_acheteurDI;
import com.news.dao_GT.Dossier_clientDI;
import com.news.dao_GT.Paiement_terrainDI;
import com.news.dao_GT.TerrainDI;

@WebServlet("/excel")
public class Fiche_terrain extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Client_acheteurDI client_acheteurDI= new Client_acheteurDI();
	Client_acheteur client_acheteur= new Client_acheteur();
	
	TerrainDI terrainDI= new TerrainDI();
	Terrain terrain= new Terrain();
	
	Paiement_terrainDI paiement_terrainDI= new Paiement_terrainDI();
	Paiement_terrain paiement_terrain= new Paiement_terrain();

	Dossier_clientDI dossier_clientDI= new Dossier_clientDI();
	Dossier_client dossier_client= new Dossier_client();
	
	Agence agence = new Agence();
	Site site = new Site();
	Users users	=	new Users();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String crud	=	request.getParameter("crud");
		String page	=	request.getParameter("page");
		String id	=	request.getParameter("id");
		String type	=	request.getParameter("type");
		
		
		List<Client_acheteur> listCl = client_acheteurDI.getAllClient_acheteur();
		List<Dossier_client> listDl = dossier_clientDI.getAllDossier_client();
		
		
		if(type.equals("CL_excel")) {	
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet ficheNote = workbook.createSheet("Fiche Liste Client");
			 CreationHelper createHelper = workbook.getCreationHelper();  
			CellStyle cellStyle = workbook.createCellStyle(); 
			
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy"));
			
			XSSFRow row0 = ficheNote.createRow(0);
			ficheNote.setColumnWidth((short)0, (short)(6*256));
			ficheNote.setColumnWidth((short)1, (short)(35*256));
			ficheNote.setColumnWidth((short)2, (short)(15*256));
			ficheNote.setColumnWidth((short)3, (short)(15*256));
			ficheNote.setColumnWidth((short)4, (short)(20*256));
			ficheNote.setColumnWidth((short)5, (short)(20*256));
			ficheNote.setColumnWidth((short)6, (short)(20*256));
			ficheNote.setColumnWidth((short)7, (short)(20*256));
			ficheNote.setColumnWidth((short)8, (short)(17*256));			
			ficheNote.setColumnWidth((short)9, (short)(13*256));
			ficheNote.setColumnWidth((short)10, (short)(13*256));
			ficheNote.setColumnWidth((short)11, (short)(28*256));
			ficheNote.setColumnWidth((short)12, (short)(17*256));
			
			row0.createCell(0,CellType.STRING).setCellValue("ID");
			row0.createCell(1,CellType.STRING).setCellValue("Nom et Prenoms");
			row0.createCell(2,CellType.STRING).setCellValue("sexe");
			row0.createCell(3,CellType.STRING).setCellValue("date naissance");
			row0.createCell(4,CellType.STRING).setCellValue("nationnalite");
			row0.createCell(5,CellType.STRING).setCellValue("situation_matrimonial");
			row0.createCell(6,CellType.STRING).setCellValue("profession");
			row0.createCell(7,CellType.STRING).setCellValue("adresse");
			row0.createCell(8,CellType.STRING).setCellValue("contact");		
			row0.createCell(9,CellType.STRING).setCellValue("Lot");
			row0.createCell(10,CellType.STRING).setCellValue("Ilot");
			row0.createCell(11,CellType.STRING).setCellValue("Lotissement");
			row0.createCell(12,CellType.STRING).setCellValue("date_achat");
			
			int index = 0;
			for(Client_acheteur list : listCl) {
				
				index	+=	 1;
				
				row0 = ficheNote.createRow(index);		
				row0.createCell(0).setCellValue(index);
				row0.createCell(1).setCellValue(list.getNom()+" "+list.getPrenom());
				row0.createCell(2).setCellValue(list.getSexe());				
				row0.createCell(3).setCellValue(list.getDate_naissance());
				row0.getCell(3).setCellStyle(cellStyle);
				row0.createCell(4).setCellValue(list.getNationnalite());
				row0.createCell(5).setCellValue(list.getSituation_matr());
				row0.createCell(6).setCellValue(list.getProfession());
				row0.createCell(7).setCellValue(list.getAdresse());			
				row0.createCell(8).setCellValue(list.getContact());				
				row0.createCell(9).setCellValue(list.getTerrains().getLot());
				row0.createCell(10).setCellValue(list.getTerrains().getIlot());
				row0.createCell(11).setCellValue(list.getTerrains().getLotissement());			
				row0.createCell(12).setCellValue(list.getTerrains().getDate_achat());
				row0.getCell(12).setCellStyle(cellStyle);
			
			}
			try{
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename=Fiche Liste Client.xlsx");
				OutputStream out = response.getOutputStream();
				workbook.write(out);
				out.close();
				
			}  catch (FileNotFoundException e) {
				e.printStackTrace();        
			} catch (IOException e) { 
				e.printStackTrace();        
			}
				
			}
		
		if(type.equals("CLD_excel")) {	
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet ficheNote = workbook.createSheet("Fiche Liste Client");
			 CreationHelper createHelper = workbook.getCreationHelper();  
			CellStyle cellStyle = workbook.createCellStyle(); 
			
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy"));
			
			XSSFRow row0 = ficheNote.createRow(0);
			ficheNote.setColumnWidth((short)0, (short)(6*256));
			ficheNote.setColumnWidth((short)1, (short)(35*256));
			ficheNote.setColumnWidth((short)2, (short)(15*256));
			ficheNote.setColumnWidth((short)3, (short)(15*256));
			ficheNote.setColumnWidth((short)4, (short)(20*256));
			ficheNote.setColumnWidth((short)5, (short)(20*256));
			ficheNote.setColumnWidth((short)6, (short)(20*256));
			ficheNote.setColumnWidth((short)7, (short)(20*256));
			ficheNote.setColumnWidth((short)8, (short)(17*256));			
			ficheNote.setColumnWidth((short)9, (short)(13*256));
			ficheNote.setColumnWidth((short)10, (short)(13*256));
			ficheNote.setColumnWidth((short)11, (short)(28*256));
			ficheNote.setColumnWidth((short)12, (short)(17*256));			
			row0.createCell(0,CellType.STRING).setCellValue("ID");
			row0.createCell(1,CellType.STRING).setCellValue("Nom et Prenoms");
			row0.createCell(2,CellType.STRING).setCellValue("sexe");
			row0.createCell(3,CellType.STRING).setCellValue("date naissance");
			row0.createCell(4,CellType.STRING).setCellValue("nationnalite");
			row0.createCell(5,CellType.STRING).setCellValue("situation_matrimonial");
			row0.createCell(6,CellType.STRING).setCellValue("profession");
			row0.createCell(7,CellType.STRING).setCellValue("adresse");
			row0.createCell(8,CellType.STRING).setCellValue("contact");			
			row0.createCell(9,CellType.STRING).setCellValue("Lot");
			row0.createCell(10,CellType.STRING).setCellValue("Ilot");
			row0.createCell(11,CellType.STRING).setCellValue("Lotissement");
			row0.createCell(12,CellType.STRING).setCellValue("date_achat");
			
			int index = 0;
			for(Dossier_client list : listDl) {
				
				index	+=	 1;
				
				row0 = ficheNote.createRow(index);		
				row0.createCell(0).setCellValue(index);
				row0.createCell(1).setCellValue(list.getNom()+" "+list.getPrenom());
				row0.createCell(2).setCellValue(list.getSexe());				
				row0.createCell(3).setCellValue(list.getDate_naissance());
				row0.getCell(3).setCellStyle(cellStyle);
				row0.createCell(4).setCellValue(list.getNationnalite());
				row0.createCell(5).setCellValue(list.getSituation_matr());
				row0.createCell(6).setCellValue(list.getProfession());
				row0.createCell(7).setCellValue(list.getAdresse());			
				row0.createCell(8).setCellValue(list.getTel());
				row0.createCell(9).setCellValue(list.getLot());
				row0.createCell(10).setCellValue(list.getIlot());
				row0.createCell(11).setCellValue(list.getLotissement());			
				row0.createCell(12).setCellValue(list.getDate_achat());
				row0.getCell(12).setCellStyle(cellStyle);

			
			}
			try{
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename=Fiche Liste Client.xlsx");
				OutputStream out = response.getOutputStream();
				workbook.write(out);
				out.close();
				
			}  catch (FileNotFoundException e) {
				e.printStackTrace();        
			} catch (IOException e) { 
				e.printStackTrace();        
			}
				
			}	
		
		
 //-----------------------------------------------Fiche Suivi ACD------------------------------------------  -->		
		if(type.equals("FSD_excel")) {	
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet ficheNote = workbook.createSheet("Fiche Suivi ACD");
						
			XSSFRow row0 = ficheNote.createRow(0);
			ficheNote.setColumnWidth((short)0, (short)(6*256));
			ficheNote.setColumnWidth((short)1, (short)(35*256));
			ficheNote.setColumnWidth((short)2, (short)(12*256));
			ficheNote.setColumnWidth((short)3, (short)(16*256));
			ficheNote.setColumnWidth((short)4, (short)(16*256));
			ficheNote.setColumnWidth((short)5, (short)(12*256));
			ficheNote.setColumnWidth((short)6, (short)(16*256));
			ficheNote.setColumnWidth((short)7, (short)(12*256));
			ficheNote.setColumnWidth((short)8, (short)(12*256));
			
			ficheNote.setColumnWidth((short)9, (short)(12*256));
			ficheNote.setColumnWidth((short)10, (short)(12*256));
			ficheNote.setColumnWidth((short)11, (short)(12*256));
			ficheNote.setColumnWidth((short)12, (short)(12*256));
			ficheNote.setColumnWidth((short)13, (short)(12*256));
			ficheNote.setColumnWidth((short)14, (short)(12*256));
			ficheNote.setColumnWidth((short)15, (short)(12*256));
			ficheNote.setColumnWidth((short)16, (short)(12*256));
			ficheNote.setColumnWidth((short)17, (short)(12*256));
			
			row0.createCell(0,CellType.STRING).setCellValue("ID");
			row0.createCell(1,CellType.STRING).setCellValue("Nom et Prenoms");
			row0.createCell(2,CellType.STRING).setCellValue("depot_DATTC");
			row0.createCell(3,CellType.STRING).setCellValue("dossier_techn_CT");
			row0.createCell(4,CellType.STRING).setCellValue("dossier_techn_D");
			row0.createCell(5,CellType.STRING).setCellValue("attes_CD");
			row0.createCell(6,CellType.STRING).setCellValue("intro_dem_ACD");
			row0.createCell(7,CellType.STRING).setCellValue("date_BM");
			row0.createCell(8,CellType.STRING).setCellValue("N° DM");
			
			row0.createCell(9,CellType.STRING).setCellValue("Trans DM");
			row0.createCell(10,CellType.STRING).setCellValue("Bornage Con");
			row0.createCell(11,CellType.STRING).setCellValue("Trans PV BC");
			row0.createCell(12,CellType.STRING).setCellValue("Créat TF (N°)");
			row0.createCell(13,CellType.STRING).setCellValue("Trans TF");
			row0.createCell(14,CellType.STRING).setCellValue("Créat ACD");
			row0.createCell(15,CellType.STRING).setCellValue("Notif ACD");
			row0.createCell(16,CellType.STRING).setCellValue("P f ACD ");
			row0.createCell(17,CellType.STRING).setCellValue("Ret ACD");
			
			
			
			int index = 0;
			for(Dossier_client list : listDl) {
				
				index	+=	 1;
				
				row0 = ficheNote.createRow(index);		
				row0.createCell(0).setCellValue(index);
				row0.createCell(1).setCellValue(list.getNom()+" "+list.getPrenom());
				if(list.getDepot_DATTC()== 1) { 
				row0.createCell(2).setCellValue("OK");
				} else {
					row0.createCell(2).setCellValue(" ");
				}
				if(list.getDossier_techn_CT()== 1) { 
				row0.createCell(3).setCellValue("OK");
				} else {
					row0.createCell(3).setCellValue(" ");
				}
				if(list.getDossier_techn_D()== 1) { 
				row0.createCell(4).setCellValue("OK");
				} else {
					row0.createCell(4).setCellValue(" ");
				}
				if(list.getAttes_CD()== 1) { 
				row0.createCell(5).setCellValue("OK");
				} else {
					row0.createCell(5).setCellValue(" ");
				}
				if(list.getIntro_dem_ACD()== 1) { 
					row0.createCell(6).setCellValue("OK");
					} else {
						row0.createCell(6).setCellValue(" ");
					}
				if(list.getDate_BM()== 1) { 
					row0.createCell(7).setCellValue("OK");
					} else {
						row0.createCell(7).setCellValue(" ");
					}
				if(list.getN_DM()== 1) { 
					row0.createCell(8).setCellValue("OK");
					} else {
						row0.createCell(8).setCellValue(" ");
					}
				if(list.getTrans_DM()== 1) { 
					row0.createCell(9).setCellValue("OK");
					} else {
						row0.createCell(9).setCellValue(" ");
					}
				if(list.getBornage_cont()== 1) { 
					row0.createCell(10).setCellValue("OK");
					} else {
						row0.createCell(10).setCellValue(" ");
					}
				if(list.getTransp_pv_bc()== 1) { 
					row0.createCell(11).setCellValue("OK");
					} else {
						row0.createCell(11).setCellValue(" ");
					}		
				if(list.getCreat_TF()== 1) { 
					row0.createCell(12).setCellValue("OK");
					} else {
						row0.createCell(12).setCellValue(" ");
					}
				if(list.getTrans_TF()== 1) { 
					row0.createCell(13).setCellValue("OK");
					} else {
						row0.createCell(13).setCellValue(" ");
					}
				if(list.getCreat_ACD()== 1) { 
					row0.createCell(14).setCellValue("OK");
					} else {
						row0.createCell(14).setCellValue(" ");
					}
				if(list.getNotif_ACD()== 1) { 
					row0.createCell(15).setCellValue("OK");
					} else {
						row0.createCell(15).setCellValue(" ");
					}
				if(list.getPf_ACD()== 1) { 
					row0.createCell(16).setCellValue("OK");
					} else {
						row0.createCell(16).setCellValue(" ");
					}
				if(list.getRef_ACD()== 1) { 
					row0.createCell(17).setCellValue("OK");
					} else {
						row0.createCell(17).setCellValue(" ");
					}
			}
		try{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename=Fiche Suivi ACD.xlsx");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.close();
			
		}  catch (FileNotFoundException e) {
			e.printStackTrace();        
		} catch (IOException e) { 
			e.printStackTrace();        
		}
			
		}	
		
	if(type.equals("FS_excel")) {	
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet ficheNote = workbook.createSheet("Fiche Suivi ACD");
		 CreationHelper createHelper = workbook.getCreationHelper();  
		CellStyle cellStyle = workbook.createCellStyle(); 
		
	//	cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy"));
		
		XSSFRow row0 = ficheNote.createRow(0);
		ficheNote.setColumnWidth((short)0, (short)(6*256));
		ficheNote.setColumnWidth((short)1, (short)(35*256));
		ficheNote.setColumnWidth((short)2, (short)(12*256));
		ficheNote.setColumnWidth((short)3, (short)(16*256));
		ficheNote.setColumnWidth((short)4, (short)(16*256));
		ficheNote.setColumnWidth((short)5, (short)(12*256));
		ficheNote.setColumnWidth((short)6, (short)(16*256));
		ficheNote.setColumnWidth((short)7, (short)(12*256));
		ficheNote.setColumnWidth((short)8, (short)(12*256));
		
		ficheNote.setColumnWidth((short)9, (short)(12*256));
		ficheNote.setColumnWidth((short)10, (short)(12*256));
		ficheNote.setColumnWidth((short)11, (short)(12*256));
		ficheNote.setColumnWidth((short)12, (short)(12*256));
		ficheNote.setColumnWidth((short)13, (short)(12*256));
		ficheNote.setColumnWidth((short)14, (short)(12*256));
		ficheNote.setColumnWidth((short)15, (short)(12*256));
		ficheNote.setColumnWidth((short)16, (short)(12*256));
		ficheNote.setColumnWidth((short)17, (short)(12*256));
		
		row0.createCell(0,CellType.STRING).setCellValue("ID");
		row0.createCell(1,CellType.STRING).setCellValue("Nom et Prenoms");
		row0.createCell(2,CellType.STRING).setCellValue("depot_DATTC");
		row0.createCell(3,CellType.STRING).setCellValue("dossier_techn_CT");
		row0.createCell(4,CellType.STRING).setCellValue("dossier_techn_D");
		row0.createCell(5,CellType.STRING).setCellValue("attes_CD");
		row0.createCell(6,CellType.STRING).setCellValue("intro_dem_ACD");
		row0.createCell(7,CellType.STRING).setCellValue("date_BM");
		row0.createCell(8,CellType.STRING).setCellValue("N° DM");
		
		row0.createCell(9,CellType.STRING).setCellValue("Trans DM");
		row0.createCell(10,CellType.STRING).setCellValue("Bornage Con");
		row0.createCell(11,CellType.STRING).setCellValue("Trans PV BC");
		row0.createCell(12,CellType.STRING).setCellValue("Créat TF (N°)");
		row0.createCell(13,CellType.STRING).setCellValue("Trans TF");
		row0.createCell(14,CellType.STRING).setCellValue("Créat ACD");
		row0.createCell(15,CellType.STRING).setCellValue("Notif ACD");
		row0.createCell(16,CellType.STRING).setCellValue("P f ACD ");
		row0.createCell(17,CellType.STRING).setCellValue("Ret ACD");
		
		
		
		int index = 0;
		for(Client_acheteur list : listCl) {
			
			index	+=	 1;
			
			row0 = ficheNote.createRow(index);		
			row0.createCell(0).setCellValue(index);
			row0.createCell(1).setCellValue(list.getNom()+" "+list.getPrenom());
			if(list.getDepot_DATTC()== 1) { 
			row0.createCell(2).setCellValue("OK");
			} else {
				row0.createCell(2).setCellValue(" ");
			}
			if(list.getDossier_techn_CT()== 1) { 
			row0.createCell(3).setCellValue("OK");
			} else {
				row0.createCell(3).setCellValue(" ");
			}
			if(list.getDossier_techn_D()== 1) { 
			row0.createCell(4).setCellValue("OK");
			} else {
				row0.createCell(4).setCellValue(" ");
			}
			if(list.getAttes_CD()== 1) { 
			row0.createCell(5).setCellValue("OK");
			} else {
				row0.createCell(5).setCellValue(" ");
			}
			if(list.getIntro_dem_ACD()== 1) { 
				row0.createCell(6).setCellValue("OK");
				} else {
					row0.createCell(6).setCellValue(" ");
				}
			if(list.getDate_BM()== 1) { 
				row0.createCell(7).setCellValue("OK");
				} else {
					row0.createCell(7).setCellValue(" ");
				}
			if(list.getN_DM()== 1) { 
				row0.createCell(8).setCellValue("OK");
				} else {
					row0.createCell(8).setCellValue(" ");
				}
			if(list.getTrans_DM()== 1) { 
				row0.createCell(9).setCellValue("OK");
				} else {
					row0.createCell(9).setCellValue(" ");
				}
			if(list.getBornage_cont()== 1) { 
				row0.createCell(10).setCellValue("OK");
				} else {
					row0.createCell(10).setCellValue(" ");
				}
			if(list.getTransp_pv_bc()== 1) { 
				row0.createCell(11).setCellValue("OK");
				} else {
					row0.createCell(11).setCellValue(" ");
				}		
			if(list.getCreat_TF()== 1) { 
				row0.createCell(12).setCellValue("OK");
				} else {
					row0.createCell(12).setCellValue(" ");
				}
			if(list.getTrans_TF()== 1) { 
				row0.createCell(13).setCellValue("OK");
				} else {
					row0.createCell(13).setCellValue(" ");
				}
			if(list.getCreat_ACD()== 1) { 
				row0.createCell(14).setCellValue("OK");
				} else {
					row0.createCell(14).setCellValue(" ");
				}
			if(list.getNotif_ACD()== 1) { 
				row0.createCell(15).setCellValue("OK");
				} else {
					row0.createCell(15).setCellValue(" ");
				}
			if(list.getPf_ACD()== 1) { 
				row0.createCell(16).setCellValue("OK");
				} else {
					row0.createCell(16).setCellValue(" ");
				}
			if(list.getRef_ACD()== 1) { 
				row0.createCell(17).setCellValue("OK");
				} else {
					row0.createCell(17).setCellValue(" ");
				}
		}
	try{
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "inline; filename=Fiche Suivi ACD.xlsx");
		OutputStream out = response.getOutputStream();
		workbook.write(out);
		out.close();
		
	}  catch (FileNotFoundException e) {
		e.printStackTrace();        
	} catch (IOException e) { 
		e.printStackTrace();        
	}
		
	}
	}

}
