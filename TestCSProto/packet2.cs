//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------


// Generated from: packet2.proto
// Note: requires additional types generated from: common.proto
namespace test
{
  
  //---------------------------------------------------------------------------------------
  [global::System.Serializable, global::ProtoBuf.ProtoContract(Name=@"Test2Request")]
  public partial class Test2Request : global::ProtoBuf.IExtensible
  {
    public Test2Request() {}
    
    
    private int _testId;
    [global::ProtoBuf.ProtoMember(1, IsRequired = true, Name=@"testId", DataFormat = global::ProtoBuf.DataFormat.TwosComplement)]
    public int testId
    {
      get { return _testId; }
      set { _testId = value; }
    }
    private global::ProtoBuf.IExtension extensionObject;
    global::ProtoBuf.IExtension global::ProtoBuf.IExtensible.GetExtensionObject(bool createIfMissing)
      { return global::ProtoBuf.Extensible.GetExtensionObject(ref extensionObject, createIfMissing); }
  }
  
  
  //---------------------------------------------------------------------------------------
  [global::System.Serializable, global::ProtoBuf.ProtoContract(Name=@"Test2Response")]
  public partial class Test2Response : global::ProtoBuf.IExtensible
  {
    public Test2Response() {}
    
    
    private common.CommonNode _testCommonNode;
    [global::ProtoBuf.ProtoMember(1, IsRequired = true, Name=@"testCommonNode", DataFormat = global::ProtoBuf.DataFormat.Default)]
    public common.CommonNode testCommonNode
    {
      get { return _testCommonNode; }
      set { _testCommonNode = value; }
    }
    private global::ProtoBuf.IExtension extensionObject;
    global::ProtoBuf.IExtension global::ProtoBuf.IExtensible.GetExtensionObject(bool createIfMissing)
      { return global::ProtoBuf.Extensible.GetExtensionObject(ref extensionObject, createIfMissing); }
  }
  
  
  //---------------------------------------------------------------------------------------
  
  public interface ITest2Service
  {
    test.Test2Response Test2(test.Test2Request request);
    
  }
    
  
}
  