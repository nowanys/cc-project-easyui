package com.cjhme.system.main.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cjhme.system.main.bean.ManyTreeNodeBean;
import com.cjhme.system.main.bean.TreeNodeBean;

/**
 * 
 * <p>
 * Title: CreateTreeUtil.java
 * </p>
 * Description: 创建树工具类
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Apr 27, 2014 8:25:13 AM
 */
public class CreateTreeUtil {

	/**
	 * 多树节点
	 */
	private ManyTreeNodeBean manyTreeNodeBean;

	/**
	 * 树根默认id
	 */
	private static String ROOT_TREE_NODE_ID = "1";

	/**
	 * 树首节点状态
	 */
	private static String FRIST_STATE = "open";

	/**
	 * 构造方法
	 */
	public CreateTreeUtil() {
		TreeNodeBean TreeNodeBean = new TreeNodeBean("1", "root", "icons-root", "-1");
		this.manyTreeNodeBean = new ManyTreeNodeBean(TreeNodeBean);
	}

	/**
	 * 创建树
	 */
	public static CreateTreeUtil createTree(List<TreeNodeBean> treeNodeList) throws Exception {

		// 判断列表是否为空
		if (null == treeNodeList || treeNodeList.size() <= 0) {
			return null;
		}

		CreateTreeUtil createTreeUtil = new CreateTreeUtil();

		// 遍历列表，构造树
		for (TreeNodeBean node : treeNodeList) {

			// 如果父id与树根相同或没有父id，添加树根
			if (node.getParentId().equals(ROOT_TREE_NODE_ID) || node.getParentId().equals("") || node.getParentId().equals("-1")) {
				node.setState(FRIST_STATE);
				createTreeUtil.getManyTreeNodeBean().getChildTreeList().add(new ManyTreeNodeBean(node));

				// 添加子节点
			} else {
				nodeAddChild(createTreeUtil.getManyTreeNodeBean(), node);
			}
		}

		return createTreeUtil;
	}

	/**
	 * 添加子节点
	 */
	private static void nodeAddChild(ManyTreeNodeBean manyTreeNodeBean, TreeNodeBean node) {

		// 遍历列表，构造树
		for (ManyTreeNodeBean manyTreeNode : manyTreeNodeBean.getChildTreeList()) {

			// 如果id与父节点id相同直接添加
			if (manyTreeNode.getTreeNodeBean().getId().equals(node.getParentId())) {
				manyTreeNode.getChildTreeList().add(new ManyTreeNodeBean(node));
				break;

				// 添加子节点
			} else {
				if (null != manyTreeNode.getChildTreeList() && manyTreeNode.getChildTreeList().size() > 0) {
					nodeAddChild(manyTreeNode, node);
				}
			}
		}

	}

	/**
	 * 获得树JSON
	 */
	public static String getTreeJson(CreateTreeUtil createTreeUtil) throws Exception {
		if (null == createTreeUtil || "".equals(createTreeUtil)) {
			return "[]";
		}

		StringBuffer json = new StringBuffer();
		json.append("[");
		createTreeJson(createTreeUtil.getManyTreeNodeBean(), json);
		json.append("]");

		return json.toString();
	}

	/**
	 * 创建树JSON
	 */
	public static void createTreeJson(ManyTreeNodeBean manyTreeNodeBean, StringBuffer json) {
		if (null == manyTreeNodeBean) {
			return;
		}

		List<ManyTreeNodeBean> childTreeList = manyTreeNodeBean.getChildTreeList();

		if (null != childTreeList && childTreeList.size() > 0) {
			for (int i = 0; i < childTreeList.size(); i++) {

				TreeNodeBean treeNodeBean = childTreeList.get(i).getTreeNodeBean();
				json.append("{");
				json.append("'id':'" + treeNodeBean.getId() + "',");
				json.append("'text':'" + treeNodeBean.getText() + "',");
				json.append("'iconCls':'" + treeNodeBean.getIconCls() + "',");

				Map<String, String> attributes = treeNodeBean.getAttributes();
				if (attributes.size() > 0) {
					json.append("'attributes':{");
					int j = 0;
					for (Map.Entry<String, String> entry : attributes.entrySet()) {
						json.append("'" + entry.getKey() + "':'" + entry.getValue() + "'");

						// 最后一个不添加逗号
						if (j != (attributes.entrySet().size() - 1)) {
							json.append(",");
						}

						j++;
					}
					json.append("},");
				}

				List<ManyTreeNodeBean> childTrees = childTreeList.get(i).getChildTreeList();
				if (null != childTrees && childTrees.size() > 0) {
					if (null != treeNodeBean.getState() && !"".equals(treeNodeBean.getState())) {
						json.append("'state':'" + treeNodeBean.getState() + "',");
					} else {
						json.append("'state':'closed',");
					}

					json.append("'children':[");
					createTreeJson(childTreeList.get(i), json);
					json.append("]");
				} else {

					if (null != treeNodeBean.getState() && !"".equals(treeNodeBean.getState())) {
						json.append("'state':'" + treeNodeBean.getState() + "'");
					} else {
						json.append("'state':'open'");
					}
				}

				// 最后一个不添加逗号
				if (i == (childTreeList.size() - 1)) {
					json.append("}");
				} else {
					json.append("},");
				}

			}
		}

	}

	/**
	 * 获得树JSON
	 */
	public static String getTreeJson(List<TreeNodeBean> treeNodes) throws Exception {
		if (null == treeNodes || "".equals(treeNodes) || treeNodes.size() <= 0) {
			return "[]";
		}

		StringBuffer json = new StringBuffer();
		json.append("[");
		createTreeJson(treeNodes, json);
		json.append("]");

		return json.toString();
	}

	/**
	 * 创建树JSON
	 */
	public static void createTreeJson( List<TreeNodeBean> treeNodes, StringBuffer json) {

		if (null != treeNodes && treeNodes.size() > 0) {
			for (int i = 0; i < treeNodes.size(); i++) {

				TreeNodeBean treeNodeBean = treeNodes.get(i);
				json.append("{");
				json.append("'id':'" + treeNodeBean.getId() + "',");
				json.append("'text':'" + treeNodeBean.getText() + "',");
				json.append("'iconCls':'" + treeNodeBean.getIconCls() + "',");

				Map<String, String> attributes = treeNodeBean.getAttributes();
				if (attributes.size() > 0) {
					json.append("'attributes':{");
					int j = 0;
					for (Map.Entry<String, String> entry : attributes.entrySet()) {
						json.append("'" + entry.getKey() + "':'" + entry.getValue() + "'");

						// 最后一个不添加逗号
						if (j != (attributes.entrySet().size() - 1)) {
							json.append(",");
						}

						j++;
					}
					json.append("},");
				}

				List<TreeNodeBean> childTrees = treeNodeBean.getChildren();
				if (null != childTrees && childTrees.size() > 0) {
					if (null != treeNodeBean.getState() && !"".equals(treeNodeBean.getState())) {
						json.append("'state':'" + treeNodeBean.getState() + "',");
					} else {
						json.append("'state':'closed',");
					}

					json.append("'children':[");
					createTreeJson(childTrees, json);
					json.append("]");
				} else {

					if (null != treeNodeBean.getState() && !"".equals(treeNodeBean.getState())) {
						json.append("'state':'" + treeNodeBean.getState() + "'");
					} else {
						json.append("'state':'open'");
					}
				}

				// 最后一个不添加逗号
				if (i == (treeNodes.size() - 1)) {
					json.append("}");
				} else {
					json.append("},");
				}

			}
		}

	}

	/**
	 * 设置root节点id
	 */
	public static void setRootTreeNodeId(String rootId) {
		ROOT_TREE_NODE_ID = rootId;
	}

	/**
	 * 设置树首节点状态
	 */
	public static void setFristState(String state) {
		FRIST_STATE = state;
	}

	public ManyTreeNodeBean getManyTreeNodeBean() {
		return manyTreeNodeBean;
	}

	public void setManyTreeNodeBean(ManyTreeNodeBean manyTreeNodeBean) {
		this.manyTreeNodeBean = manyTreeNodeBean;
	}

	public static void main(String[] args) {
		List<TreeNodeBean> treeNodeList = new ArrayList<TreeNodeBean>();
		TreeNodeBean TreeNodeBean0 = new TreeNodeBean("1", "dd", "icons-persion-center", "");
		TreeNodeBean0.setAttr1("aaaa");
		TreeNodeBean0.setAttr2("bbbb");
		TreeNodeBean0.setAttr3("bbbb");
		TreeNodeBean0.setAttr4("bbbb");
		TreeNodeBean0.setAttr5("bbbb");
		TreeNodeBean0.setAttr6("bbbb");

		TreeNodeBean TreeNodeBean1 = new TreeNodeBean("2", "哎呀", "icons-persion-center", "");
		TreeNodeBean1.setAttr1("aaaa");
		TreeNodeBean1.setAttr2("bbbb");
		TreeNodeBean1.setAttr3("bbbb");
		TreeNodeBean1.setAttr4("bbbb");
		TreeNodeBean1.setAttr5("bbbb");
		TreeNodeBean1.setAttr6("bbbb");

		TreeNodeBean TreeNodeBean2 = new TreeNodeBean("3", "在在", "icons-persion-center", "");
		TreeNodeBean TreeNodeBean3 = new TreeNodeBean("4", "CreateTreeDemo", "icons-persion-center", "");
		TreeNodeBean TreeNodeBean4 = new TreeNodeBean("5", "CreateTreeDemo", "icons-persion-center", "6");
		TreeNodeBean TreeNodeBean5 = new TreeNodeBean("6", "CreateTreeDemo", "icons-persion-center", "3");
		TreeNodeBean TreeNodeBean6 = new TreeNodeBean("7", "CreateTreeDemo", "icons-persion-center", "3");
		TreeNodeBean TreeNodeBean7 = new TreeNodeBean("8", "CreateTreeDemo", "icons-persion-center", "3");
		TreeNodeBean TreeNodeBean8 = new TreeNodeBean("9", "CreateTreeDemo־", "icons-persion-center", "4");
		TreeNodeBean TreeNodeBean9 = new TreeNodeBean("10", "CreateTreeDemo", "icons-persion-center", "6");
		TreeNodeBean TreeNodeBean10 = new TreeNodeBean("10", "CreateTreeDemo", "icons-persion-center", "7");

		treeNodeList.add(TreeNodeBean0);
		treeNodeList.add(TreeNodeBean1);
		treeNodeList.add(TreeNodeBean2);
		treeNodeList.add(TreeNodeBean3);
		treeNodeList.add(TreeNodeBean4);
		treeNodeList.add(TreeNodeBean5);
		treeNodeList.add(TreeNodeBean6);
		treeNodeList.add(TreeNodeBean7);
		treeNodeList.add(TreeNodeBean8);
		treeNodeList.add(TreeNodeBean9);
		treeNodeList.add(TreeNodeBean10);
		CreateTreeUtil createTreeUtil;
		try {
			System.out.println(System.currentTimeMillis());
			createTreeUtil = CreateTreeUtil.createTree(treeNodeList);
			System.out.println(CreateTreeUtil.getTreeJson(createTreeUtil));
			System.out.println(System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}