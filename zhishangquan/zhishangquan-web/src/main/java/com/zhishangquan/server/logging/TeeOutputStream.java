package com.zhishangquan.server.logging;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author songjz
 */
public class TeeOutputStream extends FilterOutputStream {

  public TeeOutputStream(OutputStream out) {
    super(out);
  }

  /** the second OutputStream to write to */
  protected OutputStream branch;

  /**
   * Constructs a TeeOutputStream.
   * 
   * @param out the main OutputStream
   * @param branch the second OutputStream
   */
  public TeeOutputStream(OutputStream out, OutputStream branch) {
    super(out);
    this.branch = branch;
  }

  /** @see java.io.OutputStream#write(byte[]) */
  public synchronized void write(byte[] b) throws IOException {
    out.write(b);
    this.branch.write(b);
  }

  /** @see java.io.OutputStream#write(byte[], int, int) */
  public synchronized void write(byte[] b, int off, int len) throws IOException {
    out.write(b, off, len);
    this.branch.write(b, off, len);
  }

  /** @see java.io.OutputStream#write(int) */
  public synchronized void write(int b) throws IOException {
    out.write(b);
    this.branch.write(b);
  }

  /**
   * Flushes both streams.
   *
   * @see java.io.OutputStream#flush()
   */
  public void flush() throws IOException {
    out.flush();
    this.branch.flush();
  }

  /**
   * Closes both streams.
   *
   * @see java.io.OutputStream#close()
   */
  public void close() throws IOException {
    out.close();
    this.branch.close();
  }
}
