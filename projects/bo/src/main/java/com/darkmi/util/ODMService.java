package com.darkmi.util;


/**
 * Description: 主要有生成offering,删除offering,查询产品，查询业务
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2010-12-1 上午11:38:16 laojiang created
 */
public interface ODMService {

//	/**
//	 * Description: 生成offering
//	 * @Version1.0 2010-12-1 下午01:30:46 laojiang created
//	 * @param packageAsset  实体类的PackageAsset
//	 * @param injectionProductList 注入的产品
//	 * @throws ServiceException
//	 */
//	List<Long> createOffering(PackageAsset packageAsset, List<InjectionProduct> injectionProductList,
//			List<Long> categoryList) throws ServiceException;
//
//	/**
//	 * Description: 删除Offering
//	 * @Version1.0 2010-12-1 下午01:32:10 laojiang created
//	 * @param offeringId
//	 * @throws ServiceException
//	 */
//	void deleteOffering(Long offeringId) throws ServiceException;
//
//	/**
//	 * Description: 修改offering状态
//	 * @Version1.0 2010-12-1 下午01:35:01 laojiang created
//	 * @param offeringId
//	 * @param state
//	 * @throws ServiceException
//	 */
//	void updateOfferingState(Long offeringId, Offering.StateEnum state) throws ServiceException;
//
//	/**
//	 * Description:查询provider信息
//	 * @Version1.0 2011-1-6 上午09:43:38 laojiang created
//	 * @param providerId
//	 * @return
//	 */
//	List<Provider> queryProvider(String providerId);
//
//	/**
//	 * Description: 查询产品
//	 * @Version1.0 2010-12-1 下午01:35:48 laojiang created
//	 * @return
//	 */
//	List<Product> queryProduct(String providerId, String productId);
//
//	/**
//	 * Description: 查询业务
//	 * @Version1.0 2010-12-1 下午01:36:23 laojiang created
//	 * @return
//	 */
//	List<Service> queryService(String productId, String serviceId);
//
//	/**
//	 * Description: 查询offering
//	 * @Version1.0 2010-12-1 下午01:38:24 laojiang created
//	 * @param providerId
//	 * @param productId
//	 * @param serviceId
//	 * @param offeringId
//	 * @return
//	 */
//	List<Offering> queryOffering(String providerId, String productId, String serviceId, Long offeringId);
//
//	/**
//	 * Description: 查询哪些产品和业务生成了offering
//	 * @Version1.0 2010-12-1 下午01:39:03 laojiang created
//	 * @param packageAssetId
//	 * @return
//	 */
//	List<Product> queryOfferingByAssetId(String packageAssetId);
//
//	/**
//	 * Description: 生成海报
//	 * @Version1.0 2010-12-1 下午01:39:50 laojiang created
//	 * @param photoFtpUrl
//	 * @param stillImageAssetId
//	 * @throws ServiceException
//	 */
//	String createPhoto(String photoFtpUrl, String stillImageAssetId) throws ServiceException;
//
//	/**
//	 * Description:删除海报
//	 * @Version1.0 2010-12-1 下午01:40:26 laojiang created
//	 * @param stillImageAssetId
//	 * @throws ServiceException
//	 */
//	void deletePhoto(String stillImageAssetId) throws ServiceException;
//
//	/**
//	 * Description: 得到分类列表
//	 * @Version1.0 2010-12-22 上午08:31:49 laojiang created
//	 * @param parentId 分类ID
//	 * @return
//	 * @throws ServiceException
//	 */
//	List<SpecificationChapter> getCategoryList(Long parentId) throws ServiceException;
//
//	/**
//	 * 
//	 * Description: 得到选中的tree id
//	 * @Version1.0 2010-12-22 下午02:07:45 laojiang created
//	 * @param assetId
//	 * @return
//	 * @throws ServiceException
//	 */
//	List<Long> getCheckCategoryList(String assetId) throws ServiceException;
//
//	/**
//	 * Description: 得到树的ID
//	 * @Version1.0 2011-1-26 上午11:12:52 laojiang created
//	 * @param treeIndex
//	 * @return
//	 * @throws ServiceException
//	 */
//	Long getCategoryId(String treeIndex) throws ServiceException;
//
//	/**
//	 * Description: 根据ID得到分类
//	 * @Version1.0 2011-4-19 下午01:49:41 laojiang created
//	 * @param id
//	 * @return
//	 * @throws ServiceException
//	 */
//	SpecificationChapter getCategory(Long id) throws ServiceException;
}
