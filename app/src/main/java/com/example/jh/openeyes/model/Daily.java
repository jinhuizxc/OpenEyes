package com.example.jh.openeyes.model;

import java.util.List;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class Daily {

    public String nextPageUrl;
    public List<IssueList> issueList;

    public static class IssueList {
        public long releaseTime;
        public String type;
        public long date;
        public long publishTime;
        public int count;
        public List<ItemList> itemList;
    }
}
