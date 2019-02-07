package handler;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jdbc.connection.ConnectionProvider;
import user.User;
import user.UserDAO;

public class EditMemberHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int idx = 0;
		String id = "";
		String password = "";
		String mname = "";
		String photo = "";

		// 1. multipart/form-data 여부 확인
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 2. 메모리나 파일로 업로드 파일 보관하는 FileItem의 Factory 설정
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			// 3. 업로드 요청을 처리하는 ServletFileUpload 생성
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			// 4. 업로드 요청 파싱해서 FileItem 목록 구함
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			
			while (iter.hasNext()) {
				
				FileItem item = iter.next();
				
				// 5. FileItem이 폼 입력 항목인지 여부에 따라 알맞은 처리
				if (item.isFormField()) { // 텍스트 입력인 경우
					
					String name = item.getFieldName();
					if(name.equals("id")) id = item.getString("utf-8");
					else if(name.equals("name")) mname = item.getString("utf-8");
					else if(name.equals("password")) password = item.getString("utf-8");
					else if(name.equals("idx")) idx = Integer.parseInt(item.getString("utf-8"));
					
				} else { // 파일 업로드인 경우
					
					String name = item.getFieldName();
					String fileName = item.getName();

					String uploadURI = "/file";
					String dir = request.getSession().getServletContext().getRealPath(uploadURI);
					
					File file = new File(dir, fileName);
					System.out.println(fileName);
					item.write(file);
					photo = "/file/"+fileName;
					
				}
			}
		}
		
		User u = new User(id, password, mname, photo);
		u.setIdx(idx);
		
		UserDAO dao = new UserDAO();
		int row = dao.updateUser(ConnectionProvider.getConnection(), u);
		
		if(row != -1) {
			request.setAttribute("msg", "수정 성공");
		} else {
			request.setAttribute("msg", "수정 실패");
		}
		
		return "/memberList.do";
	}

}
