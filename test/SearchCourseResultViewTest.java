import interface_adapter.search_course_result.SearchCourseResultState;
import interface_adapter.search_course_result.SearchCourseResultViewModel;
import view.FrameModel;
import view.SearchCourseResultView;
import view.SearchCourseView;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class SearchCourseResultViewTest {
    private SearchCourseResultView view;
    private SearchCourseResultState currState;
    @Before
    public void init(){
        JFrame f = new FrameModel("SearchResultView");
        SearchCourseResultViewModel viewModel = new SearchCourseResultViewModel();
        SearchCourseResultState state = viewModel.getState();

        Map<String, Map<String, String>> map = new HashMap<>();
        for (int i = 1; i<= 10; i++){
            Map<String, String> inner_map = new HashMap<>();
            inner_map.put("nickname", "Name"+i);
            inner_map.put("description", "BRO, Iam Name"+i);
            map.put("userID"+i, inner_map);
        }
        state.setResultUsers(map);
        viewModel.setState(state);
        currState = state;
        // SearchCourseResultView vieww = new SearchCourseResultView(viewModel);
        // view = vieww;
        f.add(view);
        f.setVisible(true);
    }
    @Test
    public void testSearchCourseResultViewUpdate(){
        // view.updateUsers(currState.getResultUsers(), currState.getResultUserTags());
    }
}
