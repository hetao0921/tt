delete from nvmp.Nvmp_AllCenterCommandGroupRelationTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_AllCenterCommandGroupTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_AllCenterUserInfoTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_CameraGroupRelationTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_CameraGroupTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_CenterInfoSyncTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_CenterNetWorkInfoTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_CommandDevCHTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_CommandDevTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_CommandGroupRelationTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_CommandGroupUserRelationTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_CommandTeamTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_DepartmentTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_DevRightTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_FuncRightTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_LoopPlanCHRelationTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_VideoLoopPlanTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_OperateLogTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_PlatSvrDevTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_PrePointRouteTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_PrePointSetTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_RecordFileTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_RoleInfoTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_SystemLogTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_UserInfoTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_UserRoleRelationTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_VideoDevCHTab where centerid='000BAB65C2ED@001';
delete from nvmp.Nvmp_VideoDevTab where centerid='000BAB65C2ED@001';
insert into nvmp.Nvmp_CommandDevTab(DeviceID,DeviceName,DeviceDesc,DevIP,DevMAC,WorkStatus,IsOnline,ClientUserID,CommandStatus,ConferenceStatus,ConsultationStatus,DevModal,DevVer,CameraNum,CommandTeamID,CenterID) values('005008057994@006','172','''','192.168.1.172','''',0,0,'0',0,0,0,0,'指挥终端8010',0,'0','000BAB65C2ED@001');
insert into nvmp.Nvmp_CommandDevTab(DeviceID,DeviceName,DeviceDesc,DevIP,DevMAC,WorkStatus,IsOnline,ClientUserID,CommandStatus,ConferenceStatus,ConsultationStatus,DevModal,DevVer,CameraNum,CommandTeamID,CenterID) values('7427EA1BD6A5@006','lch','''','...','''',0,0,'''',0,0,0,0,'指挥终端8010',0,'''','000BAB65C2ED@001');
