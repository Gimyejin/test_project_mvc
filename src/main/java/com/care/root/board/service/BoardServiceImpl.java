package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.common.MemberSessionName;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper mapper;
	@Autowired
	BoardFileService bfs;

	public void selectAllBoardList(Model model) {
		model.addAttribute("boardList", mapper.selectAllBoardList());
	}

	public String writeSave(MultipartHttpServletRequest mul, HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setTitle(mul.getParameter("title"));
		dto.setContent(mul.getParameter("content"));

		dto.setId(mul.getParameter("id"));
		// HttpSession session = request.getSession();
		// dto.setId((String)session.getAttribute(MemberSessionName.LOGIN));

		MultipartFile file = mul.getFile("image_file_name");
		// BoardFileService bfs = new BoardFileServiceImpl();
		if (file.getSize() != 0) {
			// 이미지 있을경우 처리
			dto.setImageFileName(bfs.saveFile(file));

		} else {
			dto.setImageFileName("nan");
		}
		int result = 0;
		try {// 사용자에게는 에러메시지가 뜨면 안되니 트라이 케치함
			result = mapper.writeSave(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bfs.getMessage(result, request);
	}

	@Override
	public void contentView(int writeNo, Model model) {
		model.addAttribute("personalData", mapper.contentView(writeNo));
		upHit(writeNo);
	}

	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}

	public String boardDelete(int writeNo, String imageFileName, HttpServletRequest request) {
		// BoardFileService bfs = new BoardFileServiceImpl();
		String message = null;
		int result = mapper.delete(writeNo);
		if (result == 1) {
			bfs.deleteImage(imageFileName);
			message = bfs.getMessage(request, "삭제 성공", "/board/boardAllList");
		} else {
			message = bfs.getMessage(request, "삭제 실패", "/board/contentView");
		}
		return message;
	}

	@Override
	public void getDate(int writeNo, Model model) {
		model.addAttribute("personalData", mapper.contentView(writeNo));
	}

	public String modify(MultipartHttpServletRequest mul, HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setWriteNo(Integer.parseInt(mul.getParameter("writeNo")));
		dto.setTitle(mul.getParameter("title"));
		dto.setContent(mul.getParameter("content"));

		MultipartFile file = mul.getFile("imageFileName");
		if (file.getSize() != 0) {
			// 이미지 변경시 원래 있던 이미지 삭제, 이미지 이름 변경
			dto.setImageFileName(bfs.saveFile(file));
			bfs.deleteImage(mul.getParameter("originFileName"));
		} else {
			dto.setImageFileName(mul.getParameter("originFileName"));
		}
		int result = mapper.modify(dto);
		String msg, url;
		if (result == 1) {
			msg = "성공적으로 수정되었습니다";
			url = "/board/boardAllList";
		} else {
			msg = "수정 중 문제가 발생하였습니다";
			url = "/board/modify_form";
		}
		String message = bfs.getMessage(request, msg, url);
		return message;
	}

}
