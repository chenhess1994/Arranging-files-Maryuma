package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import entity.MyFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class WindowController implements Initializable {

	@FXML
	private TextField ClientTxtField;

	@FXML
	private TextField productTxtField;

	@FXML
	private Button selectButton;

	@FXML
	private Button SubmitButton;

	@FXML
	private Text error1;

	@FXML
	private Text error2;

	@FXML
	private TextArea filesTxt;
	private List<File> filelist;
	private Stage stage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		filesTxt.setVisible(true);
		
		error1.setVisible(false);
		error2.setVisible(false);
	}

	private void Showalert(String Header, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Header);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	private Boolean formChecker() {
		boolean flag = true;
		if (ClientTxtField.getText().isEmpty()) {
			error1.setVisible(true);
			flag = false;
		} else
			error1.setVisible(false);
		if (productTxtField.getText().isEmpty()) {
			error2.setVisible(true);
			flag = false;
		} else
			error2.setVisible(false);

		return flag;
	}

	@FXML
	void SubmitButton(ActionEvent event) {
		if (!formChecker())
			return;
		//openDirectory("D:\\java project\\HenDesignerZip\\"+ ClientTxtField.getText() + "_"+ productTxtField.getText() + "\\");
		// change path here
		openDirectory("D:\\MARYUMA עיצובים ואמנות\\customers\\" + ClientTxtField.getText() + "_"
				+ productTxtField.getText() + "\\");
		SetFiles(filelist);
	}

	private void openDirectory(String strDirectoy) {
		try {
			// Create one directory
			boolean success = (new File(strDirectoy)).mkdir();
			if (success) {
				System.out.println("Directory: " + strDirectoy + " created");
				Showalert("The file in is place", "Hen good work the file in the place");
			}

		} catch (Exception e) {
			System.out.println("error in folder create");
			Showalert("Open file", "Directory Error, please call Hen 052-2209718");
		}
	}

	@FXML
	void selectButton(ActionEvent event) {
		if (!formChecker())
			return;

		openFile();
		Showalert("The files", "This files you select:\n"+printNameFiles(filelist));
	}

	/**
	 * create the open browser.
	 */
	private void openFile() {
		try {
			FileChooser fileChooser = new FileChooser();
			filelist = fileChooser.showOpenMultipleDialog(stage);
			fileChooser.setTitle("Open Resource File");
			filesTxt.setText(printNameFiles(filelist));
		} catch (Exception e) {
			System.out.println("error file");
			
			e.printStackTrace();
		}
	}

	private void SetFiles(List<File> filelist) {
		Path to;
		Path from;
		// change path here
		String pathDst = "D:\\MARYUMA עיצובים ואמנות\\customers\\" + ClientTxtField.getText() + "_"
				+ productTxtField.getText() + "\\";
		for (File i : filelist) {
			if (filelist != null) {
				try {
					if (filelist != null) {
						from = Paths.get(i.toURI());
						to = Paths.get(pathDst + i.getName());
						Files.copy(from, to);
					}
				} catch (IOException e) {
					Showalert("error move file", "error move file");
				}
			}
		}
	}

	private String printNameFiles(List<File> filelist) {
		StringBuilder str = new StringBuilder("");
		if (filelist != null) {
			for (File i : filelist) {
				str.append(i.getName());
				str.append("\n");
			}
		}
		return str.toString();
	}

//	/**
//	 * Zip a list of file into one zip file.
//	 *
//	 * @param files         files to zip
//	 * @param targetZipFile target zip file
//	 * @throws IOException IO error exception can be thrown when copying ...
//	 */
//	public static void zipFile(final File[] files, final File targetZipFile) throws IOException {
//		try {
//			FileOutputStream fos = new FileOutputStream(targetZipFile);
//			ZipOutputStream zos = new ZipOutputStream(fos);
//			byte[] buffer = new byte[256];
//			for (int i = 0; i < files.length; i++) {
//				File currentFile = files[i];
//				if (!currentFile.isDirectory()) {
//					ZipEntry entry = new ZipEntry(currentFile.getName());
//					FileInputStream fis = new FileInputStream(currentFile);
//					zos.putNextEntry(entry);
//					int read = 0;
//					while ((read = fis.read(buffer)) != -1) {
//						zos.write(buffer, 0, read);
//					}
//					zos.closeEntry();
//					fis.close();
//				}
//			}
//			zos.close();
//			fos.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("File not found : " + e);
//		}
//
//	}

}
