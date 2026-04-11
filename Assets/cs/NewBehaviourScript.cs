using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class Boundary
{
    public float xMin, xMax, zMin, zMax;
}

public class NewBehaviourScript : MonoBehaviour
{
    public float speed;
    public float tilt;
    public Boundary boundary;
    public Rigidbody rb;
    public float speed2;


    public GameObject shot;
    public Transform shotspawn;
    private Rigidbody rb2;
    public float firerate;

    private float nextfire;
    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody>();
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetButton("Fire1") && Time.time > nextfire)
        {
            nextfire = Time.time + firerate;
            GameObject clone =
            Instantiate(shot, shotspawn.position, shotspawn.rotation);
            clone.SetActive(true);
            rb2 = clone.GetComponent<Rigidbody>();
            rb2.velocity = transform.forward * speed2;
        }
    }
    void FixedUpdate()
    {
        float MoveHorizontal = Input.GetAxis("Horizontal");
        float MoveVertical = Input.GetAxis("Vertical");
        Vector3 movement = new Vector3(MoveHorizontal, 0.0f, MoveVertical);
        rb.velocity = movement * speed;

        rb.position = new Vector3
        (
            Mathf.Clamp(rb.position.x, boundary.xMin, boundary.xMax),
            0.0f,
            Mathf.Clamp(rb.position.z, boundary.zMin, boundary.zMax)
        );
        rb.rotation = Quaternion.Euler(0.0f,0.0f,rb.velocity.x*-tilt);

    }
}
