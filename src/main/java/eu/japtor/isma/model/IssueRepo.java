/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.util.List;

/**
 *
 * @author Honza
 */
public interface IssueRepo {

    public List<Issue> getIssues(Integer aMaxRec);
    public List<Comment> getIssueComments(Issue issue);
    public Issue getIssueById(Long id);
    public Long createIssue(Issue issue);
//    public int updateComment(Comment comment);
//    public int deleteCommentById(Long id);
}
