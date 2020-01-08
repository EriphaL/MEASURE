package com.nbst.comnutil;

/**
 * 全局常量定义
 * 
 * @author huangh
 *
 */
public class GlobalConstant {

	// 操作成功编号
	public final static String SUCCESS_CODE = "0000";
	// 新增成功
	public final static String INSERT_SUCCESS = "新增成功";
	// 修改成功
	public final static String UPDATE_SUCCESS = "修改成功";
	// 查询成功
	public final static String SEARCH_SUCCESS = "查询成功";
	// 删除成功
	public final static String DELETE_SUCCESS = "删除成功";
	// 存入数据库成功
	public final static String Store_In_Database = "存入数据库成功";
	// 登录成功
	public final static String LOGIN_SUCCESS_MESSAGE = "登录成功";
	// 验证成功
	public final static String VERIFY_SUCCESS_MESSAGE = "验证成功";
	// 新增失败编号
	public final static String INSERT_FAILURE_CODE = "0001";
	// 新增失败消息
	public final static String INSERT_FAILURE_MESSAGE = "新增失败";
	// 修改失败编号
	public final static String UPDATE_FAILURE_CODE = "0002";
	// 修改失败消息
	public final static String UPDATE_FAILURE_MESSAGE = "修改失败";
	// 查询失败编号
	public final static String SEARCH_FAILURE_CODE = "0003";
	// 查询失败消息
	public final static String SEARCH_FAILURE_MESSAGE = "查询失败";
	// 删除失败编号
	public final static String DELETE_FAILURE_CODE = "0004";
	// 删除失败消息
	public final static String DELETE_FAILURE_MESSAGE = "删除失败";
	// 名字重复修改失败编号
	public final static String UPDATE_FAILURE_REPEAT = "0006";
	// 名字重复修改失败编号
	public final static String UPDATE_FAILURE_MREPEAT = "名字重复修改失败";
	// 检测通过
	public final static String DETECION_WRONG_CODE = "0005";
	// 检测通过
	public final static String DETECION_PASS = "检测通过";
	// 检测到数据异常
	public final static String DETECION_WRONG = "检测到数据异常";
	// 已存在相同的编号
	public final static String SAME_NUMBER_CODE = "0006";
	// 已存在相同的编号
	public final static String SAME_NUMBER = "已存在相同的编号";
	// 上下限相同
	public final static String SAME_NUMBERICAL_CODE = "0007";
	// 上下限相同
	public final static String SAME_NUMBERICAL = "输入的上下限相同";
	// 控制图未关联不良定义
	public final static String NO_CORRELATION_MESSAGE_CODE = "0008";
	// 控制图未关联不良定义
	public final static String NO_CORRELATION_MESSAGE = "找不到关联信息";
	// 未接收到id
	public final static String NO_ID_CODE = "0009";
	// 未接收到id
	public final static String NO_ID_MESSAGE = "未接收到id";
	// 未查到数据
	public final static String NO_SEARCH_DATA_CODE = "0010";
	// 未查到数据
	public final static String NO_SEARCH_DATA_MESSAGE = "未查到数据";
	// 代号不唯一code
	public final static String CODE_NOT_UNIQUE_CODE = "0011";
	// 代号不唯一message
	public final static String CODE_NOT_UNIQUE_MESSAGE = "已存在相同的编号";
	// 数据点未处理
	public final static String DATA_NOT_PROCESSED_CODE = "0012";
	// 数据点未处理
	public final static String DATA_NOT_PROCESSED_MESSAGE = "数据点未处理";
	// 代号不唯一code
	public final static String DETECTION_ID_NOT_UNIQUE_CODE = "0013";
	// 代号不唯一message
	public final static String DETECTION_ID_NOT_UNIQUE_MESSAGE = "检测数据项id不唯一";
	// 代号不唯一code
	public final static String DATA_UNAUDITED_CODE = "0014";
	// 代号不唯一message
	public final static String DATA_UNAUDITED_MESSAGE = "失控未审核";
	// 数据关联出错
	public final static String DATA_RELATE_ERROR_CODE = "0015";
	// 数据关联出错
	public final static String DATA_RELATE_ERROR_MESSAGE = "数据关联出错";
	// 未接收到控制图编号
	public final static String NO_CONTROL_CHART_NUMBER_CODE = "0016";
	// 未接收到控制图编号
	public final static String NO_CONTROL_CHART_NUMBER = "未接收到控制图编号";
	// 该数据已被绑定
	public final static String DATA_BE_OCCUPIED_CODE = "0017";
	// 该数据已被绑定
	public final static String DATA_BE_OCCUPIED = "该数据已被绑定";
	// 检测数据正常
	public final static String DETECTION_DATA_NORMAL_CODE = "0018";
	// 检测数据正常
	public final static String DETECTION_DATA_NORMAL = "检测数据正常";
	// 控制图内已存在数据
	public final static String CONTROL_CHART_HAVE_DATA_CODE = "0019";
	// 控制图内已存在数据
	public final static String CONTROL_CHART_HAVE_DATA = "控制图内已存在数据，禁止修改样本容量";
	// 账号名不存在
	public final static String NONENTITY_USERNAME_CODE = "0020";
	// 账号名不存在
	public final static String NONENTITY_USERNAME_MESSAGE = "账号名不存在";
	// 登录密码错误
	public final static String PASSWORD_WORING_CODE = "0021";
	// 登录密码错误
	public final static String PASSWORD_WORING_MESSAGE = "登录密码错误";
	// 验证超时
	public final static String PASS_VERIFY_TIME_CODE = "0022";
	// 验证超时
	public final static String PASS_VERIFY_TIME_MESSAGE = "验证超时";
	// 传入点均相同
	public final static String POINT_EQUAL_CODE = "0023";
	// 传入点均相同
	public final static String POINT_EQUAL_MESSAGE = "传入点均相同";
	// 异常报错
	public final static String WORING_CODE = "0024";
	// 当前页面已失效
	public final static String CURRENT_PAGE_INVALID_CODE = "0025";
	// 当前页面已失效
	public final static String CURRENT_PAGE_INVALID_MESSAGE = "当前页面已失效";
	// 数据正常状态
	public final static String NORMAL_STATE = "1";
	// 数据软删状态
	public final static String DELETE_STATE = "2";
	// 不存在父节点时pid的值
	public final static String NEGATION_PID = "0";
	// 克隆控制图（控制图信息及数据）
	public final static Integer CLONE_MESSAGE_TABLE_STRUCTURE_AND_DATA = 1;
	// 修改控制图状态
	public final static Integer UPDATE_CONTROL_CHART_STATE = 2;
	// 失控记录已审核状态
	public final static Integer AUDITED = 1;
	// 失控记录未审核状态
	public final static Integer NOT_AUDITED = 0;
	// 检测数据正常状态
	public final static Integer DETECTION_DATA_JCSJ_STATE_NORMAL = 1;
	// 检测数据失控状态
	public final static Integer DETECTION_DATA_JCSJ_STATE_ABNORMAL = 2;
	// 检测数据已处理状态
	public final static Integer DETECTION_DATA_JCSJ_STATE_PROCESSED = 3;
	// XR图名称
	public final static String XR_NAME = "XR";
	// XS图名称
	public final static String XS_NAME = "XS";
	// IMR图名称
	public final static String IMR_NAME = "IMR";
	// nP图名称
	public final static String NP_NAME = "nP";
	// R0
	public final static String R0 = "R0";
	// R1
	public final static String R1 = "R1";
	// 分组条件：样本容量
	public final static String SAMPLE_CAPACITY = "sampleCapacity";
	// 分组条件：控制图类型id
	public final static String CONTROL_CHART_TYPE_ID = "typeId";
	// 分组条件：上限
	public final static String UPPER_LIMIT = "upperLimit";
	// 分组条件：目标值
	public final static String TARGET_VALUE = "targetValue";
	// 分组条件：下限
	public final static String DOWN_LIMIT = "downLimit";
	// 分组条件：检测项id
	public final static String DETECTION_ITEM_ID = "detectionItemId";
	// 分组条件：分组名称
	public final static String GROUP_NAME = "groupName";
	// 该控制图最后一条数据未被填满
	public final static String LAST_DATA = "该控制图最后一条数据未被填满";
	// 该控制图最后一条数据被填满
	public final static String LAST_DATA_SUCCESS = "该控制图最后一条数据被填满";
	//该控制图没有数据
	public final static String DATA_NULL = "该控制图没有数据";
	// 该控制图最后一条数据未被填满code
	public final static String LAST_DATA_CODE = "0099";

	// X-R和X-S的参数
	public static double[] A2 = new double[] { 0, 0, 1.880, 1.023, 0.729, 0.577, 0.483, 0.419, 0.373, 0.337, 0.308,
			0.285, 0.266, 0.249, 0.235, 0.223, 0.212, 0.203, 0.194, 0.187, 0.180, 0.173, 0.167, 0.162, 0.157, 0.153 };
	public static double[] D2 = { 1.0, 1.0, 1.128, 1.693, 2.059, 2.326, 2.543, 2.704, 2.847, 2.970, 3.078, 3.173, 3.258,
			3.336, 3.407, 3.472, 3.532, 3.588, 3.640, 3.689, 3.735, 3.778, 3.819, 3.858, 3.895, 3.931 };
	public static double[] D3 = new double[] { 0, 0, 0, 0, 0, 0, 0, 0.076, 0.136, 0.184, 0.223, 0.256, 0.283, 0.307,
			0.328, 0.347, 0.363, 0.378, 0.391, 0.403, 0.415, 0.425, 0.434, 0.443, 0.451, 0.459 };
	public static double[] D4 = new double[] { 0, 0, 3.267, 2.574, 2.282, 2.114, 2.004, 1.924, 1.864, 1.816, 1.777,
			1.717, 1.693, 1.672, 1.653, 1.637, 1.622, 1.608, 1.597, 1.585, 1.575, 1.566, 1.557, 1.548, 1.541 };
	public static double[] A3 = new double[] { 0, 0, 2.659, 1.954, 1.628, 1.427, 1.287, 1.182, 1.099, 1.032, 0.975,
			0.927, 0.886, 0.850, 0.817, 0.789, 0.763, 0.739, 0.718, 0.698, 0.680, 0.663, 0.647, 0.633, 0.619, 0.606 };
	// n 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 20 25
	public static double[] C4 = new double[] { 0, 0, 0.7979, 0.8862, 0.9213, 0.9400, 0.9515, 0.9594, 0.9650, 0.9693,
			0.9727, 0.9754, 0.9776, 0.9794, 0.9810, 0.9823, 0.9835, 0.9854, 0.9854, 0.9862, 0.9869, 0.9876, 0.9882,
			0.9887, 0.9892, 0.9896 }; // ,0.9869,0.9896};
	public static double[] B3 = new double[] { 0, 0, 0, 0, 0, 0, 0.030, 0.118, 0.185, 0.239, 0.284, 0.321, 0.354, 0.382,
			0.406, 0.428, 0.448, 0.446, 0.482, 0.497, 0.510, 0.523, 0.534, 0.545, 0.555, 0.565 }; // , 0.510, 0.565};
	public static double[] B4 = new double[] { 0, 0, 3.276, 2.568, 2.266, 2.089, 1.970, 1.882, 1.815, 1.761, 1.716,
			1.679, 1.640, 1.618, 1.594, 1.572, 1.552, 1.534, 1.518, 1.503, 1.490, 1.477, 1.466, 1.455, 1.445, 1.435 };
	// 正态分布函数数值
	public static double[][] PPM = { { 0.5, 0.496, 0.492, 0.488, 0.484, 0.48, 0.476, 0.472, 0.468, 0.464 },
			{ 0.46, 0.456, 0.452, 0.448, 0.444, 0.44, 0.436, 0.433, 0.426, 0.425 },
			{ 0.421, 0.417, 0.413, 0.409, 0.405, 0.401, 0.397, 0.394, 0.39, 0.386 },
			{ 0.382, 0.378, 0.375, 0.371, 0.367, 0.363, 0.359, 0.356, 0.352, 0.348 },
			{ 0.345, 0.341, 0.337, 0.334, 0.33, 0.326, 0.323, 0.319, 0.316, 0.312 },
			{ 0.309, 0.305, 0.302, 0.298, 0.295, 0.291, 0.288, 0.284, 0.281, 0.278 },
			{ 0.274, 0.271, 0.268, 0.264, 0.261, 0.258, 0.255, 0.251, 0.248, 0.245 },
			{ 0.242, 0.239, 0.236, 0.233, 0.23, 0.227, 0.224, 0.221, 0.218, 0.215 },
			{ 0.212, 0.209, 0.206, 0.203, 0.201, 0.198, 0.195, 0.192, 0.189, 0.187 },
			{ 0.184, 0.181, 0.179, 0.176, 0.174, 0.171, 0.169, 0.166, 0.164, 0.161 },
			{ 0.159, 0.156, 0.154, 0.152, 0.148, 0.147, 0.145, 0.142, 0.14, 0.138 },
			{ 0.136, 0.134, 0.131, 0.129, 0.127, 0.125, 0.123, 0.121, 0.119, 0.117 },
			{ 0.115, 0.113, 0.111, 0.109, 0.108, 0.106, 0.104, 0.102, 0.1, 0.0985 },
			{ 0.0968, 0.0915, 0.0934, 0.0918, 0.901, 0.0885, 0.0869, 0.0853, 0.0838, 0.0823 },
			{ 0.0808, 0.0792, 0.0778, 0.0764, 0.0749, 0.0735, 0.0721, 0.0708, 0.0694, 0.0681 },
			{ 0.0668, 0.0655, 0.0643, 0.063, 0.0618, 0.0606, 0.0594, 0.0582, 0.0571, 0.0559 },
			{ 0.0548, 0.0537, 0.0526, 0.0516, 0.0505, 0.0495, 0.0485, 0.0475, 0.0465, 0.0455 },
			{ 0.0446, 0.0436, 0.0427, 0.0418, 0.0409, 0.0401, 0.0392, 0.0384, 0.0375, 0.0367 },
			{ 0.0359, 0.0352, 0.0344, 0.0336, 0.0329, 0.0322, 0.0314, 0.0307, 0.0301, 0.0294 },
			{ 0.0287, 0.0281, 0.0274, 0.0268, 0.0262, 0.0265, 0.025, 0.0244, 0.0239, 0.0233 },
			{ 0.0228, 0.0222, 0.0217, 0.0212, 0.0207, 0.0202, 0.0197, 0.0192, 0.0188, 0.0183 },
			{ 0.0179, 0.0174, 0.017, 0.0166, 0.0162, 0.0158, 0.0154, 0.015, 0.0146, 0.0143 },
			{ 0.0139, 0.0136, 0.0132, 0.0129, 0.0126, 0.0122, 0.0119, 0.0116, 0.0113, 0.011 },
			{ 0.0107, 0.0104, 0.0102, 0.0099, 0.00964, 0.00939, 0.00914, 0.00889, 0.00866, 0.00842 },
			{ 0.0082, 0.00798, 0.00776, 0.00755, 0.00734, 0.00714, 0.00695, 0.00676, 0.00657, 0.00639 },
			{ 0.00621, 0.00604, 0.00587, 0.0057, 0.00554, 0.00539, 0.00523, 0.00509, 0.00494, 0.0048 },
			{ 0.00466, 0.00453, 0.0044, 0.00427, 0.00415, 0.00402, 0.00391, 0.00379, 0.00368, 0.00357 },
			{ 0.00347, 0.00336, 0.00326, 0.00317, 0.00307, 0.00298, 0.00289, 0.0028, 0.00272, 0.00264 },
			{ 0.00256, 0.00248, 0.0024, 0.00232, 0.00226, 0.00219, 0.00212, 0.00205, 0.00199, 0.00193 },
			{ 0.00187, 0.00181, 0.00175, 0.0017, 0.00164, 0.00159, 0.00154, 0.00149, 0.00144, 0.0014 },
			{ 0.00135, 0.00131, 0.00126, 0.00122, 0.00118, 0.00114, 0.00111, 0.00107, 0.00104, 0.001 },
			{ 0.000968, 0.000935, 0.000904, 0.000874, 0.000845, 0.000816, 0.000789, 0.000762, 0.000736, 0.000711 },
			{ 0.000687, 0.000664, 0.000641, 0.000619, 0.000598, 0.000577, 0.000557, 0.000538, 0.000519, 0.000501 },
			{ 0.000484, 0.000467, 0.00045, 0.000434, 0.000419, 0.000404, 0.00039, 0.000376, 0.000363, 0.00035 },
			{ 0.000337, 0.000325, 0.000313, 0.000302, 0.000291, 0.00028, 0.00027, 0.00026, 0.000251, 0.000242 },
			{ 0.000233, 0.000224, 0.000216, 0.000208, 0.0002, 0.000193, 0.000186, 0.000179, 0.000172, 0.000166 },
			{ 0.000159, 0.000153, 0.000147, 0.000142, 0.000136, 0.000131, 0.000126, 0.000121, 0.000117, 0.000112 },
			{ 0.000108, 0.000104, 0.0000997, 0.0000959, 0.0000921, 0.0000886, 0.0000851, 0.0000818, 0.0000785,
					0.0000755 },
			{ 0.0000727, 0.0000696, 0.0000669, 0.0000642, 0.0000617, 0.0000592, 0.0000568, 0.0000546, 0.0000524,
					0.0000503 },
			{ 0.0000482, 0.0000463, 0.0000444, 0.0000426, 0.0000409, 0.0000392, 0.0000376, 0.0000361, 0.0000346,
					0.0000332 },
			{ 0.0000318, 0.0000305, 0.0000292, 0.0000286, 0.0000268, 0.0000257, 0.0000247, 0.0000236, 0.0000226,
					0.0000217 } };
}
